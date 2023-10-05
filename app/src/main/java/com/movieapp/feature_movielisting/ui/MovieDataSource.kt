package com.movieapp.feature_movielisting.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.movieapp.core.domain.GetMoviesUseCase
import com.movieapp.core.domain.ProcessMovieDataUseCase
import com.movieapp.core.model.Movie
import java.lang.NullPointerException

class MovieDataSource(private val getMoviesUseCase: GetMoviesUseCase, private val processMovieDataUseCase: ProcessMovieDataUseCase) : PagingSource<Int, Movie>() {

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

            val pageNumber = params.key ?: 1
            val response = getMoviesUseCase(title = "time", page = pageNumber, year = year)
            val calculationResult = processMovieDataUseCase(year,pageNumber,response)
            val reachedEndOfResults = calculationResult.reachedEndOfResults
            val totalNumberOfFullPages = calculationResult.totalNumberOfPages

            year = calculationResult.year

            response?.let {

                LoadResult.Page(
                    data = it.movies,
                    prevKey = if (pageNumber > 0) pageNumber - 1 else null,
                    nextKey = if (pageNumber < totalNumberOfFullPages) pageNumber + 1 else {

                        if (reachedEndOfResults) {
                            null
                        } else {
                            1
                        }
                    }
                )

            }?: kotlin.run {
                LoadResult.Error(NullPointerException())
            }

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