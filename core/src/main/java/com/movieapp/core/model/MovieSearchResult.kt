package com.movieapp.core.model

import com.movieapp.core.data.model.RemoteMovieSearchResult

data class MovieSearchResult(
    val movies: List<Movie>,
    val totalResults: Int
) {
    companion object {

        fun fromRemoteResult(remoteMovieSearchResult: RemoteMovieSearchResult): MovieSearchResult {

            return MovieSearchResult(remoteMovieSearchResult.remoteMovies.map {
                Movie.fromRemoteMovie(
                    it
                )
            }, remoteMovieSearchResult.totalResults.toInt())
        }
    }
}