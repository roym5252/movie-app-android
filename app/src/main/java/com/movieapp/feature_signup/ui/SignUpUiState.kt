package com.movieapp.feature_signup.ui

import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.ErrorType

data class SignUpUiState(
    val isLoading: Boolean = false,
    val isRegistered: Boolean = false,
    val errorSection: ErrorSection? = null,
    val errorType: ErrorType? = null
)

