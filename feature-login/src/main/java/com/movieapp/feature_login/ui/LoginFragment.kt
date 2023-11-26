package com.movieapp.feature_login.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.movieapp.core.ui.BaseActivity
import com.movieapp.core.ui.BaseFragment
import com.movieapp.core.util.CommonUtil
import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.ErrorType
import com.movieapp.core.util.MessageUtil
import com.movieapp.core.util.NotificationUtil
import com.movieapp.core.util.TaskResult
import com.movieapp.core.util.NavigationUtil
import com.movieapp.feature_login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import dmax.dialog.SpotsDialog
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Login screen fragment.
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    @Inject
    lateinit var notificationUtil: NotificationUtil

    @Inject
    lateinit var commonUtil: CommonUtil

    @Inject
    lateinit var messageUtil: MessageUtil

    @Inject
    lateinit var navigationUtil: NavigationUtil

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var progressDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setViewModel()
        setObjects()
        setListeners()
        setObservers()
    }

    private fun setViewModel() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private fun setObjects() {
        progressDialog = SpotsDialog(requireActivity(), com.movieapp.core.R.style.Custom)
    }

    private fun setListeners() {
        handleLoginButtonClick()
        handleForgotPasswordClick()
        handleSignUpOptionClick()
    }

    private fun setObservers() {
        observerUiState()
    }

    private fun observerUiState() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                loginViewModel.loginUiState.collect { loginUiState ->

                    if (loginUiState.isLoading) {

                        progressDialog.show()
                        return@collect

                    } else {
                        progressDialog.dismiss()
                    }

                    if (loginUiState.authenticated) {

                        if (!requireActivity().isFinishing && isAdded) {
                            navigationUtil.navigateToMovieListingFragment(requireActivity())
                        }

                        return@collect
                    }

                    if (loginUiState.errorSection != null) {

                        loginUiState.errorType?.let {

                            if (loginUiState.errorSection == ErrorSection.CONNECTIVITY){
                                showNoConnectivityMessage(messageUtil,notificationUtil)
                            }else{

                                showWarningMessage(
                                    messageUtil,
                                    notificationUtil, loginUiState.errorSection, loginUiState.errorType
                                )

                            }

                            loginViewModel.resetErrorState()
                        }
                    }

                }
            }
        }

    }

    private fun handleLoginButtonClick() {

        binding.btnLogin.setOnClickListener() {

            commonUtil.dismissKeyBoard(requireActivity() as BaseActivity)

            loginViewModel.login(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )

        }
    }

    private fun handleForgotPasswordClick() {

        binding.txtForgotPasswordLbl.setOnClickListener() {

            showWarningMessage(
                messageUtil,
                notificationUtil,

                TaskResult.Error(
                    ErrorSection.NOT_IMPLEMENTED_FEATURE,
                    ErrorType.NOT_IMPLEMENTED_FEATURE
                )

            )
        }
    }

    private fun handleSignUpOptionClick() {

        binding.tvSignupLbl.setOnClickListener() {

            if (!requireActivity().isFinishing && isAdded) {
                navigationUtil.navigateToSignUpFragment(requireActivity())
            }
        }
    }
}