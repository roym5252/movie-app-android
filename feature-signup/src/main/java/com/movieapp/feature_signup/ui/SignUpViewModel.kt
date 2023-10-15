package com.movieapp.feature_signup.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.core.domain.MatchPasswordUseCase
import com.movieapp.core.domain.UserRegistrationUseCase
import com.movieapp.core.domain.ValidateEmailUseCase
import com.movieapp.core.domain.ValidatePasswordUseCase
import com.movieapp.core.domain.ValidatePersonNameUseCase
import com.movieapp.core.util.CommonUtil
import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.TaskResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Sign Up screen view model.
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val application: Application,
    private val commonUtil: CommonUtil,
    private val validatePersonNameUseCase: ValidatePersonNameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val matchPasswordUseCase: MatchPasswordUseCase,
    private val userRegistrationUseCase: UserRegistrationUseCase,
) : AndroidViewModel(application) {

    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val sigUpUiState: StateFlow<SignUpUiState> = _signUpUiState

    fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {

        _signUpUiState.value = SignUpUiState(true)

        viewModelScope.launch {

            if (isPersonNameValidationSuccess(firstName, ErrorSection.FIRST_NAME)
                && isPersonNameValidationSuccess(lastName, ErrorSection.LAST_NAME)
                && isEmailValidationSuccess(email)
                && isPasswordValidationSuccess(password, ErrorSection.PASSWORD)
                && isPasswordValidationSuccess(confirmPassword, ErrorSection.CONFIRM_PASSWORD)
                && isPasswordMatchValidationSuccess(password, confirmPassword)
            ) {

                when (val connectivityResult = commonUtil.checkNetworkConnectivity(application)) {

                    is TaskResult.Success -> {
                        triggerSignUp(firstName, lastName, email, password)
                    }

                    is TaskResult.Error -> {
                        _signUpUiState.value = SignUpUiState(
                            isLoading = false,
                            errorSection = connectivityResult.errorSection,
                            errorType = connectivityResult.errorType
                        )
                    }
                }


            }

        }

    }

    private fun isPersonNameValidationSuccess(name: String, errorSection: ErrorSection): Boolean {

        when (val personNameValidationResult = validatePersonNameUseCase.invoke(name)) {

            is TaskResult.Success<*> -> {
                return true
            }

            is TaskResult.Error -> {

                _signUpUiState.value = SignUpUiState(
                    isLoading = false,
                    errorSection = errorSection,
                    errorType = personNameValidationResult.errorType
                )

                return false
            }

        }
    }

    private fun isEmailValidationSuccess(email: String): Boolean {

        when (val emailValidationResult = validateEmailUseCase.invoke(email)) {

            is TaskResult.Success<*> -> {
                return true
            }

            is TaskResult.Error -> {

                _signUpUiState.value = SignUpUiState(
                    isLoading = false,
                    errorSection = emailValidationResult.errorSection,
                    errorType = emailValidationResult.errorType
                )

                return false
            }

        }
    }

    private fun isPasswordValidationSuccess(password: String, errorSection: ErrorSection): Boolean {

        when (val passwordValidationResult = validatePasswordUseCase.invoke(password)) {

            is TaskResult.Success<*> -> {
                return true
            }

            is TaskResult.Error -> {

                _signUpUiState.value = SignUpUiState(
                    isLoading = false,
                    errorSection = errorSection,
                    errorType = passwordValidationResult.errorType
                )

                return false
            }

        }
    }

    private fun isPasswordMatchValidationSuccess(
        password: String,
        confirmPassword: String
    ): Boolean {

        when (val passwordMatchValidationSuccess =
            matchPasswordUseCase.invoke(password, confirmPassword)) {

            is TaskResult.Success<*> -> {
                return true
            }

            is TaskResult.Error -> {

                _signUpUiState.value = SignUpUiState(
                    isLoading = false,
                    errorSection = passwordMatchValidationSuccess.errorSection,
                    errorType = passwordMatchValidationSuccess.errorType
                )

                return false
            }

        }
    }

    private suspend fun triggerSignUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ) {


        when (val loginResult = userRegistrationUseCase(firstName, lastName, email, password)) {

            is TaskResult.Success -> {
                _signUpUiState.value = SignUpUiState(isLoading = false, isRegistered = true)
            }

            is TaskResult.Error -> {
                _signUpUiState.value = SignUpUiState(
                    isLoading = false,
                    errorSection = loginResult.errorSection,
                    errorType = loginResult.errorType
                )
            }

        }
    }
}