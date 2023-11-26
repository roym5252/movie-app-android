package com.movieapp.feature_movielisting.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.movieapp.core.data.repository.UserRepository
import com.movieapp.core.domain.GetMoviesUseCase
import com.movieapp.core.domain.ProcessMovieDataUseCase
import com.movieapp.core.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListingViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val processMovieDataUseCase: ProcessMovieDataUseCase
) : AndroidViewModel(application) {

    private val _movieListingUiState = MutableStateFlow(MovieListingUiState())
    val movieListingUiState: StateFlow<MovieListingUiState> = _movieListingUiState

    val movies = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
        MovieDataSource(getMoviesUseCase, processMovieDataUseCase)
    }).flow.cachedIn(viewModelScope)

    fun logout() {

        viewModelScope.launch {
            userRepository.logout()
            _movieListingUiState.value =MovieListingUiState(isLoading = false, userLoggedOut = true)
        }
    }

}