package com.movieapp.feature_signup.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.movieapp.R
import com.movieapp.databinding.FragmentSignUpBinding
import com.movieapp.core.ui.BaseFragment
import com.movieapp.core.util.CommonUtil
import com.movieapp.core.util.MessageUtil
import com.movieapp.core.util.NotificationUtil
import com.movieapp.core.util.NavigationUtil
import dagger.hilt.android.AndroidEntryPoint
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Sign Up screen fragment.
 */
@AndroidEntryPoint
class SignUpFragment : BaseFragment() {

    @Inject
    lateinit var notificationUtil: NotificationUtil

    @Inject
    lateinit var commonUtil: CommonUtil

    @Inject
    lateinit var messageUtil: MessageUtil

    @Inject
    lateinit var navigationUtil: NavigationUtil

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var progressDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setViewModel()
        setUpBackNavigationOnToolbar()
        setObjects()
        setListeners()
        setObservers()
    }

    private fun setViewModel() {
        signUpViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
    }

    private fun setObjects() {
        progressDialog = SpotsDialog(requireActivity(), R.style.Custom)
    }

    private fun setListeners() {
        handleSignUpButtonClick()
    }

    private fun setObservers() {
        observerUiState()
    }

    private fun setUpBackNavigationOnToolbar() {
        binding.tbMain.setNavigationIcon(R.drawable.ic_back)
        binding.tbMain.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun handleSignUpButtonClick() {

        binding.btnSignup.setOnClickListener {

            signUpViewModel.signUp(
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etConfirmPassword.text.toString()
            )
        }
    }

    private fun observerUiState() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                signUpViewModel.sigUpUiState.collect { uiState ->

                    if (uiState.isLoading) {
                        progressDialog.show()
                        return@collect
                    } else {
                        progressDialog.dismiss()
                    }

                    if (uiState.isRegistered) {

                        if (!requireActivity().isFinishing && isAdded) {
                            navigationUtil.navigateToMovieListingFragment(requireActivity())
                        }

                        return@collect
                    }

                    if (uiState.errorSection != null) {

                        uiState.errorType?.let {

                            showWarningMessage(
                                messageUtil,
                                notificationUtil, uiState.errorSection, uiState.errorType
                            )

                        }
                    }

                }
            }
        }
    }
}