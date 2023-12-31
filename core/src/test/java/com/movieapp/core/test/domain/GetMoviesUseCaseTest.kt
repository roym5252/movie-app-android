package com.movieapp.core.test.domain

import android.util.MalformedJsonException
import com.movieapp.core.data.datasource.remote.APIInterface
import com.movieapp.core.data.model.RemoteMovieSearchResult
import com.movieapp.core.testutil.MainCoroutineRule
import com.movieapp.core.testutil.testApiKey
import com.movieapp.core.testutil.testPage
import com.movieapp.core.testutil.testTitle
import com.movieapp.core.testutil.testYear
import com.movieapp.core.testutil.validMovieJsonResponse
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.Exception


@OptIn(ExperimentalCoroutinesApi::class)
class GetMoviesUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: APIInterface

    @Before
    fun setUp() {

        mockWebServer = MockWebServer()
        mockWebServer.start()

        val moshi = Moshi.Builder().build()

        apiClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(APIInterface::class.java)

    }

    @Test
    fun get_movies_valid_response_test() = runTest {

        val mockResponse: MockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(validMovieJsonResponse)

        mockWebServer.enqueue(mockResponse)

        val call = movieApi()
        val response: Response<RemoteMovieSearchResult?> = call.execute()

        assertEquals(
            true,
            (response.body() != null
                    && (response.body()?.remoteMovies != null && response.body()?.remoteMovies!!.isNotEmpty())
                    && (response.body()?.totalResults != null && response.body()?.totalResults!!.toInt() > 0)
                    && (response.body()?.response != null && response.body()?.response!!.isNotEmpty()))
        )
    }

    @Test
    fun get_movies_empty_body_test() = runTest {

        val mockResponse: MockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("{}")

        mockWebServer.enqueue(mockResponse)

        val call = movieApi()
        val response: Response<RemoteMovieSearchResult?> = call.execute()

        assertEquals(
            true,
            (response.body() != null
                    && response.body()?.remoteMovies == null
                    && response.body()?.totalResults == null
                    && response.body()?.response == null)
        )
    }

    @Test
    fun get_movies_malformed_json_test() = runTest {

        val mockResponse: MockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("{,[}")

        mockWebServer.enqueue(mockResponse)

        val call = movieApi()

        val response = try {
            call.execute()
        } catch (exception: Exception) {
            exception
        }

        assertEquals(
            true,
            (response is JsonEncodingException || response is MalformedJsonException)
        )
    }

    @Test
    fun get_movies_non_200_response_code_test() = runTest {

        val mockResponse: MockResponse = MockResponse()
            .setResponseCode(404)
            .setBody("[]")

        mockWebServer.enqueue(mockResponse)

        val call = movieApi()
        val response = call.execute()

        assertEquals(true, response.body() == null)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun movieApi(): Call<RemoteMovieSearchResult?> {

        return apiClient.getMovies(
            testApiKey,
            testTitle,
            testYear,
            testPage
        )
    }
}