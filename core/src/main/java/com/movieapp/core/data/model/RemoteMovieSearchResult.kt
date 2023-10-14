package com.movieapp.core.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Note: Only for holding remote response. Should not be used outside of data layer.
 */
data class RemoteMovieSearchResult(

    @SerializedName("Search")
    @Expose
    val remoteMovies: List<RemoteMovie>,

    @SerializedName("totalResults")
    @Expose
    val totalResults: String,

    @SerializedName("Response")
    @Expose
    val response: String)
