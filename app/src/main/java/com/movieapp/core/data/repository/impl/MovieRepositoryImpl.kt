package com.movieapp.core.data.repository.impl

import com.movieapp.core.data.datasource.remote.APIInterface
import com.movieapp.core.data.repository.MovieRepository
import com.movieapp.core.model.Movie
import com.movieapp.core.model.MovieSearchResult
import com.movieapp.core.util.PrefUtil
import retrofit2.awaitResponse
import javax.inject.Inject

/**
 * Default implementation of movie repository interface.
 */
internal class MovieRepositoryImpl @Inject constructor(
    private val apiClient: APIInterface,
    private val prefUtil: PrefUtil
) : MovieRepository {

    override suspend fun getMovies(title: String, year: Int, page: Int): MovieSearchResult? {

        val result = apiClient.getMovies(prefUtil.getString("api_key"), title, year, page = page).awaitResponse()
        val body = result.body()

        body?.let { _ ->

            return MovieSearchResult(body.remoteMovies.map {
                Movie.fromRemoteMovie(it)
            }, body.totalResults.toInt())

        } ?: kotlin.run {
            return null
        }

    }
}