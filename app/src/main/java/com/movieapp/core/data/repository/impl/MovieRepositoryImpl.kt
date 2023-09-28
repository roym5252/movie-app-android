package com.movieapp.core.data.repository.impl

import com.movieapp.core.data.datasource.remote.APIInterface
import com.movieapp.core.data.model.MoviePageCalculationResult
import com.movieapp.core.data.repository.MovieRepository
import com.movieapp.core.model.Movie
import com.movieapp.core.model.MovieSearchResult
import com.movieapp.core.util.PrefUtil
import retrofit2.awaitResponse
import java.lang.NullPointerException
import java.util.Calendar
import javax.inject.Inject

/**
 * Default implementation of movie repository interface.
 */
internal class MovieRepositoryImpl @Inject constructor(
    private val apiClient: APIInterface,
    private val prefUtil: PrefUtil
) : MovieRepository {

    override suspend fun getMovies(title: String, year: Int, page: Int): MovieSearchResult? {

        val response = apiClient
            .getMovies(prefUtil.getString("api_key"), title, year, page = page)
            .awaitResponse()

        val body = response.body()

        if (response.code() == 200) {

            body?.let { _ ->

                return MovieSearchResult(body.remoteMovies.map {
                    Movie.fromRemoteMovie(it)
                }, body.totalResults.toInt())

            } ?: kotlin.run {
                return null
            }

        } else {
            return null
        }
    }

    override fun calculatePageToLoad(
        lastRequestedYear: Int,
        lastRequestedPage: Int,
        response: MovieSearchResult?
    ): MoviePageCalculationResult {


        response?.let {

            var year = lastRequestedYear

            val totalNumberOfResults = response.totalResults
            var totalNumberOfFullPages = totalNumberOfResults / 10

            if (totalNumberOfResults % 10 != 0) {
                totalNumberOfFullPages += 1
            }

            var reachedEndOfResults = false

            if (lastRequestedPage >= totalNumberOfFullPages) {

                val currentYear = Calendar.getInstance().get(Calendar.YEAR)

                if (currentYear > year) {
                    year++
                } else {
                    reachedEndOfResults = true
                }

            }

            return MoviePageCalculationResult(
                year,
                totalNumberOfFullPages,
                reachedEndOfResults
            )

        } ?: kotlin.run {
            throw NullPointerException()
        }

    }
}