package com.movieapp.feature_login.ui

import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.ErrorType

data class LoginUiState(
    val isLoading: Boolean = false,
    val authenticated: Boolean = false,
    val errorSection: ErrorSection? = null,
    val errorType: ErrorType? = null
)