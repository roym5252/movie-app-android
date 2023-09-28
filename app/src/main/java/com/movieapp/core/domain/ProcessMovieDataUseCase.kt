package com.movieapp.core.domain

import com.movieapp.core.data.model.MoviePageCalculationResult
import com.movieapp.core.data.model.RemoteMovieSearchResult
import com.movieapp.core.data.repository.MovieRepository
import com.movieapp.core.model.MovieSearchResult
import javax.inject.Inject

class ProcessMovieResponseUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(year:Int, page: Int,response:MovieSearchResult): MoviePageCalculationResult? {
        return movieRepository.calculatePageToLoad(lastRequestedYear = year, lastRequestedPage = page,response=response)
    }
}