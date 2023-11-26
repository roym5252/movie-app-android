package com.movieapp.core.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Note: Only for holding remote response. Should not be used outside of data layer.
 */
@JsonClass(generateAdapter = true)
data class RemoteMovieSearchResult(

    @Json(name="Search")
    val remoteMovies: List<RemoteMovie>?,

    @Json(name="totalResults")
    val totalResults: String?,

    @Json(name="Response")
    val response: String?)
