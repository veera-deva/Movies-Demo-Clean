package com.demo.data.api

import com.demo.data.model.movies.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

fun interface MovieApi {
    @GET("/movies")
    suspend fun getMovieList(): Response<List<MovieResponse>>
}