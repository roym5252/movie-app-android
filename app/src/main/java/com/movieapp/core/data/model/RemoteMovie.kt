package com.movieapp.core.data.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.movieapp.core.model.User


/**
 * Note: Only for holding remote response. Should not be used outside of data layer.
 */
data class RemoteMovie(

    @SerializedName("Title")
    @Expose
    val title: String? = null,

    @SerializedName("Year")
    @Expose
    val year: String? = null,

    @SerializedName("imdbID")
    @Expose
    val imdbID: String? = null,

    @SerializedName("Type")
    @Expose
    val type: String? = null,

    @SerializedName("Poster")
    @Expose
    val poster: String? = null
)