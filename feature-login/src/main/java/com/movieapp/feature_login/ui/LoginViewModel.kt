package com.movieapp.feature_login.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.movieapp.core.domain.UserLoginUseCase
import com.movieapp.core.util.TaskResult
import com.movieapp.core.domain.ValidateEmailUseCase
import com.movieapp.core.domain.ValidatePasswordUseCase
import com.movieapp.core.util.CommonUtil
import com.movieapp.core.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Login view model.
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val application: Application,
    private val commonUtil: CommonUtil,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val userLoginUseCase: UserLoginUseCase
) : AndroidViewModel(application) {

    private val _loginUiState = MutableLiveData(Event(LoginUiState()))
    val loginUiState: LiveData<Event<LoginUiState>> = _loginUiState

    /**For handling user login.**/
    fun login(email: String, password: String) {

        viewModelScope.launch {

            _loginUiState.value = Event(LoginUiState(true))

            if (isEmailValidationSuccess(email) &&
                isPasswordValidationSuccess(password)
            ) {

                when (val connectivityResult = commonUtil.checkNetworkConnectivity(application)) {

                    is TaskResult.Success -> {
                        triggerLogin(email, password)
                    }

                    is TaskResult.Error -> {
                        _loginUiState.value = Event(
                            LoginUiState(
                                isLoading = false,
                                authenticated = false,
                                connectivityResult.errorSection,
                                connectivityResult.errorType
                            )
                        )
                    }
                }


            }
        }

    }

    private fun isEmailValidationSuccess(email: String): Boolean {

        when (val emailValidationResult = validateEmailUseCase.invoke(email)) {

            is TaskResult.Success<*> -> {
                return true
            }

            is TaskResult.Error -> {

                _loginUiState.value = Event(
                    LoginUiState(
                        isLoading = false,
                        authenticated = false,
                        emailValidationResult.errorSection,
                        emailValidationResult.errorType
                    )
                )

                return false
            }

            else -> {
                return false
            }
        }
    }

    private fun isPasswordValidationSuccess(password: String): Boolean {

        when (val passwordValidationResult = validatePasswordUseCase.invoke(password)) {

            is TaskResult.Success<*> -> {
                return true
            }

            is TaskResult.Error -> {

                _loginUiState.value = Event(
                    LoginUiState(
                        isLoading = false,
                        authenticated = false,
                        passwordValidationResult.errorSection,
                        passwordValidationResult.errorType
                    )
                )

                return false
            }

        }
    }

    private suspend fun triggerLogin(
        email: String,
        password: String
    ) {
        when (val loginResult = userLoginUseCase(email, password)) {

            is TaskResult.Success -> {
                _loginUiState.value = Event(LoginUiState(isLoading = false, authenticated = true))
            }

            is TaskResult.Error -> {
                _loginUiState.value = Event(
                    LoginUiState(
                        isLoading = false,
                        errorSection = loginResult.errorSection,
                        errorType = loginResult.errorType
                    )
                )
            }

        }
    }
}