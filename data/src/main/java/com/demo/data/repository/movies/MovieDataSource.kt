package com.demo.data.repository.movies

import com.demo.data.model.movies.MovieResponse
import com.demo.domain.model.NetworkResult

/**
 * Data source interface for movies API
 * */
fun interface MovieDataSource {
    suspend fun getMovies(): NetworkResult<List<MovieResponse>>
}