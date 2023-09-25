package com.movieapp.core.domain

import com.movieapp.core.data.datasource.remote.APIInterface
import com.movieapp.core.data.repository.MovieRepository
import com.movieapp.core.model.MovieSearchResult
import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.ErrorType
import com.movieapp.core.util.TaskResult
import retrofit2.awaitResponse
import retrofit2.http.Query
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend operator fun invoke(title:String, year:Int, page: Int): MovieSearchResult? {
        return movieRepository.getMovies(title,year, page = page)
    }
}