package com.demo.data.api

import com.demo.data.model.MovieResponse
import retrofit2.http.GET

interface MovieApi {

    @GET("/movies")
    suspend fun getMovieList(): List<MovieResponse>
}