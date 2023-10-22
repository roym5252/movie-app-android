package com.movieapp.feature_movielisting.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.movieapp.core.ui.BaseFragment
import com.movieapp.core.util.CommonUtil
import com.movieapp.core.util.MessageUtil
import com.movieapp.core.util.NotificationUtil
import com.movieapp.core.util.NavigationUtil
import com.movieapp.feature_movielisting.databinding.FragmentMovieListingBinding
import com.movieapp.feature_movielisting.util.biometric.BiometricAuthListener
import com.movieapp.feature_movielisting.util.biometric.BiometricUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  Fragment for displaying movie list.
 */
@AndroidEntryPoint
class MovieListingFragment : BaseFragment(), BiometricAuthListener {

    private lateinit var binding: FragmentMovieListingBinding
    private lateinit var movieListingViewModel: MovieListingViewModel

    @Inject
    lateinit var notificationUtil: NotificationUtil

    @Inject
    lateinit var messageUtil: MessageUtil

    @Inject
    lateinit var biometricUtil: BiometricUtil

    @Inject
    lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var navigationUtil: NavigationUtil

    @Inject
    lateinit var commonUtil: CommonUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieListingViewModel = ViewModelProvider(this)[MovieListingViewModel::class.java]
        binding.rvMovies.layoutManager = LinearLayoutManager(requireActivity())

        setupView()
        setListeners()
        checkConnectivityAndLoadData()
        observerUiState()
    }

    private fun invokeBiometricAuth() {

        if (biometricUtil.isBiometricReady(requireActivity())) {

            biometricUtil.showBiometricPrompt(
                activity = requireActivity(),
                listener = this,
                cryptoObject = null,
                allowDeviceCredential = true
            )
        }

    }

    private fun checkConnectivityAndLoadData() {

        //Checking internet connectivity
        if (!commonUtil.isInternetConnected(requireActivity())) {

            //Handle no connectivity scenarios.
            showNoConnectivityMessage(messageUtil, notificationUtil)
            binding.spinKit.visibility = View.GONE
            binding.ibRetry.visibility = View.VISIBLE
        } else {

            //Request data and set list
            setupList()
        }

    }

    private fun setupView() {

        movieAdapter = MovieAdapter()

        binding.rvMovies.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter.withLoadStateFooter(

                LoadStateAdapter {

                    if (!commonUtil.isInternetConnected(requireActivity())) {
                        showNoConnectivityMessage(messageUtil, notificationUtil)
                    }

                    movieAdapter.retry()
                }
            )

            setHasFixedSize(true)
        }
    }

    private fun setListeners() {

        binding.ibRetry.setOnClickListener {

            if (!commonUtil.isInternetConnected(requireActivity())) {
                showNoConnectivityMessage(messageUtil, notificationUtil)
                binding.spinKit.visibility = View.GONE
                binding.ibRetry.visibility = View.VISIBLE
            } else {
                binding.ibRetry.visibility = View.GONE
                binding.spinKit.visibility = View.VISIBLE
                movieAdapter.retry()
            }

        }

        binding.toolbarLayout.btnLogout.setOnClickListener {
            movieListingViewModel.logout()
        }
    }

    private fun setupList() {

        binding.spinKit.visibility = View.VISIBLE
        binding.ibRetry.visibility = View.GONE

        //Listening loading and error states. Displaying loader/retry views based on the state.
        movieAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.NotLoading) {
                binding.spinKit.visibility = View.GONE
            } else if (loadState.refresh is LoadState.Error && movieAdapter.itemCount < 1) {
                binding.spinKit.visibility = View.GONE
                binding.ibRetry.visibility = View.VISIBLE
            }
        }

        lifecycleScope.launch {

            movieListingViewModel.movies.collectLatest { pagedData ->

                launch(Dispatchers.Main) {
                    binding.rvMovies.visibility = View.VISIBLE
                }

                movieAdapter.submitData(pagedData)

            }

        }
    }

    private fun observerUiState() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                movieListingViewModel.movieListingUiState.observe(viewLifecycleOwner) { uiState ->

                    val movieListingUiState = uiState.getContentIfNotHandled()?: return@observe

                    if (movieListingUiState.userLoggedOut) {

                        if (!requireActivity().isFinishing && isAdded) {
                            navigationUtil.navigateToLoginFragment(requireActivity())
                        }

                        return@observe
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded && !requireActivity().isFinishing)
                invokeBiometricAuth()
        }, 500)

    }

    override fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult) {
        showSuccessMessage(
            resources.getString(com.movieapp.core.R.string.verified_title),
            resources.getString(com.movieapp.core.R.string.verified_desc),
            notificationUtil
        )
    }

    override fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String) {
        if (errorCode == BiometricPrompt.ERROR_USER_CANCELED
            || errorCode == BiometricPrompt.ERROR_LOCKOUT
            || errorCode == BiometricPrompt.ERROR_LOCKOUT_PERMANENT
        ) {
            movieListingViewModel.logout()
        }
    }
}