package com.movieapp.core.model

import com.movieapp.core.data.model.RemoteMovie

data class Movie(
    val title: String?,
    val year: String?,
    val poster: String?,
    val imdbID: String?
) {
    companion object {

        fun fromRemoteMovie(remoteMovie: RemoteMovie): Movie {

            return Movie(
                remoteMovie.title,
                remoteMovie.year,
                remoteMovie.poster,
                remoteMovie.imdbID
            )
        }
    }
}