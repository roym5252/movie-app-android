package com.movieapp.core.data.datasource.remote

import com.movieapp.core.data.model.RemoteMovieSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    //API for fetching movies.
    @GET(".")
    fun getMovies(@Query("apikey")apikey:String, @Query("s")title:String="time", @Query("y") year:Int=2000, @Query("page") page: Int): retrofit2.Call<RemoteMovieSearchResult?>
}