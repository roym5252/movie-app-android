package com.movieapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movieapp.core.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model for MainActivity
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    private val _loginStatus = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _loginStatus

    init {

        viewModelScope.launch {
            _loginStatus.value = userRepository.checkLoginStatus()
        }
    }
}