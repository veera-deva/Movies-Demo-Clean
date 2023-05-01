package com.demo.data.api

import com.demo.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/movies")
    suspend fun getMovieList(): List<MovieResponse>

    @GET("movies/{id}")
    suspend fun getMovieById(@Path("id") movieId: Int): MovieResponse
}