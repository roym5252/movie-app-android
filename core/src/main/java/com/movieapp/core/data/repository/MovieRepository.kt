package com.movieapp.core.data.repository

import com.movieapp.core.data.model.MoviePageCalculationResult
import com.movieapp.core.model.MovieSearchResult

interface MovieRepository {
    suspend fun getMovies(title:String, year:Int, page: Int):MovieSearchResult?
    fun calculatePageToLoad(lastRequestedYear:Int, lastRequestedPage:Int, response: MovieSearchResult?): MoviePageCalculationResult
}