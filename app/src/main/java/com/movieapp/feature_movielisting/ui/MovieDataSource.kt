package com.movieapp.feature_movielisting.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movieapp.core.domain.GetMoviesUseCase
import com.movieapp.core.model.Movie
import com.movieapp.core.util.PrefUtil
import java.util.Calendar

class MovieDataSource(private val getMoviesUseCase: GetMoviesUseCase,private val prefUtil: PrefUtil) : PagingSource<Int, Movie>() {

    //Setting minimum year
    private var year = 2000

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        /**
         *  Pagination logic.
         *  Step 1: Calculate the number of pages by dividing total number of records in an error with 10.
         *  Step 2: If there is any reminder, then an additional call will be made by incrementing page number.
         *  Step 3: As soon as the total number of pages in an year reaches, year is incremented by 1 and page number is reset to 1.
         *  Step 4: Once the year is current year and all the pages are fetched, then null is passed to indicate that no more API calls are required.
         */
        return try {
            val nextPageNumber = params.key ?: 1
            val response = getMoviesUseCase(title = "time", page = nextPageNumber, year = year)

            val totalNumberOfResults = response!!.totalResults
            var totalNumberOfFullPages = totalNumberOfResults / 10

            if (totalNumberOfResults % 10 != 0) {
                totalNumberOfFullPages += 1
            }

            var reachedEndOfResults = false

            if (nextPageNumber >= totalNumberOfFullPages) {

                val currentYear = Calendar.getInstance().get(Calendar.YEAR)

                if (currentYear > year) {
                    year++
                } else {
                    reachedEndOfResults = true
                }

            }

            LoadResult.Page(
                data = response.remoteMovies,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < totalNumberOfFullPages) nextPageNumber + 1 else {

                    if (reachedEndOfResults) {
                        null
                    } else {
                        1
                    }
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}