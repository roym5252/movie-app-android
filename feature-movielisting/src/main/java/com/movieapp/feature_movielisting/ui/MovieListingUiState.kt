package com.movieapp.feature_movielisting.ui

import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.ErrorType

data class MovieListingUiState(
    val isLoading: Boolean = false,
    val userLoggedOut:Boolean = false,
    val errorSection: ErrorSection? = null,
    val errorType: ErrorType? = null
)