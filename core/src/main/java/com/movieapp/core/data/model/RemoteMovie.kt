package com.movieapp.core.data.model
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Note: Only for holding remote response. Should not be used outside of data layer.
 */
@JsonClass(generateAdapter = true)
data class RemoteMovie(

    @Json(name="Title")
    val title: String? = null,

    @Json(name="Year")
    val year: String? = null,

    @Json(name="imdbID")
    val imdbID: String? = null,

    @Json(name="Type")
    val type: String? = null,

    @Json(name="Poster")
    val poster: String? = null
)