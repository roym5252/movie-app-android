package com.movieapp.core.test.domain

import com.movieapp.core.data.datasource.remote.APIInterface
import com.movieapp.core.data.model.MoviePageCalculationResult
import com.movieapp.core.data.repository.MovieRepository
import com.movieapp.core.data.repository.impl.MovieRepositoryImpl
import com.movieapp.core.domain.ProcessMovieDataUseCase
import com.movieapp.core.model.Movie
import com.movieapp.core.model.MovieSearchResult
import com.movieapp.core.testutil.MainCoroutineRule
import com.movieapp.core.util.PrefUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.Calendar

@OptIn(ExperimentalCoroutinesApi::class)
class ProcessMovieDataUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var prefUtil: PrefUtil

    @Mock
    private lateinit var apiClient: APIInterface

    private lateinit var processMovieDataUseCase: ProcessMovieDataUseCase
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        movieRepository = MovieRepositoryImpl(apiClient, prefUtil)
        processMovieDataUseCase = ProcessMovieDataUseCase(movieRepository)
    }


    @Test
    fun total_page_count_test_with_fully_divisible_page_number_expected_result_5() {

        val testYear = 2000
        val testPageNumber = 1
        val testTotalResults = 50
        val expectedNumberOfPages = 5

        val moviePageCalculationResult: MoviePageCalculationResult = processMovieDataUseCase(
            testYear,
            testPageNumber,
            MovieSearchResult(getDummyMovies(), testTotalResults)
        )
        Assert.assertEquals(expectedNumberOfPages, moviePageCalculationResult.totalNumberOfPages)
    }

    @Test
    fun total_page_count_test_with_non_fully_divisible_page_number_expected_result_6() {

        val testYear = 2000
        val testPageNumber = 1
        val testTotalResults = 53
        val expectedNumberOfPages = 6

        val moviePageCalculationResult: MoviePageCalculationResult = processMovieDataUseCase(
            testYear,
            testPageNumber,
            MovieSearchResult(getDummyMovies(), testTotalResults)
        )
        Assert.assertEquals(expectedNumberOfPages, moviePageCalculationResult.totalNumberOfPages)
    }

    @Test
    fun year_increment_test_after_current_year_result_is_finished() {

        val testYear = 2000
        val testTotalResults = 50

        val expectedYear = 2001
        var actualNextYear = 0


        for (i in 1..5) {

            val moviePageCalculationResult: MoviePageCalculationResult = processMovieDataUseCase(
                testYear,
                i,
                MovieSearchResult(getDummyMovies(), testTotalResults)
            )

            if (i == 5) {
                actualNextYear = moviePageCalculationResult.year
            }
        }

        Assert.assertEquals(expectedYear, actualNextYear)
    }

    @Test
    fun year_is_not_incremented_beyond_current_year_test() {

        val testYear = Calendar.getInstance().get(Calendar.YEAR)
        val testTotalResults = 50

        val expectedYear = Calendar.getInstance().get(Calendar.YEAR)
        var actualNextYear = 0


        for (i in 1..5) {

            val moviePageCalculationResult: MoviePageCalculationResult = processMovieDataUseCase(
                testYear,
                i,
                MovieSearchResult(getDummyMovies(), testTotalResults)
            )

            if (i == 5) {
                actualNextYear = moviePageCalculationResult.year
            }
        }

        Assert.assertEquals(expectedYear, actualNextYear)
    }

    @Test
    fun total_api_call_count_and_result_end_flag_test() {

        val testStartYear = 2000
        val testEndYear = Calendar.getInstance().get(Calendar.YEAR)
        val testTotalResults = 50

        var totalApiCallCount = 0
        var reachedEndOfResult = false


        for (yearToTest in testStartYear..testEndYear) {

            for (pageNum in 1..5) {

                val moviePageCalculationResult: MoviePageCalculationResult =
                    processMovieDataUseCase(
                        yearToTest,
                        pageNum,
                        MovieSearchResult(getDummyMovies(), testTotalResults)
                    )

                totalApiCallCount += 1

                /**
                 * For making sure that reachedEndResult is assigned a new value only at the end of all loops.
                 */
                if (pageNum==5&&yearToTest==testEndYear){
                    reachedEndOfResult = moviePageCalculationResult.reachedEndOfResults
                }
            }
        }

        /**
         * Difference between current year and starting year is calculated.
         * Adding 1 for considering the iteration of current year also.
         * As total result count is set as 50, we can expect 5 iterations.
         *
         * ((Current year - Start year) + Adding 1 for starting year) * page count
         */
        Assert.assertEquals(((testEndYear - testStartYear) + 1) * 5,totalApiCallCount,)
        Assert.assertEquals(true,reachedEndOfResult )

    }


    private fun getDummyMovies(): List<Movie> {

        return listOf(
            Movie(null, null, null, null),
            Movie(null, null, null, null),
            Movie(null, null, null, null)
        )
    }

}