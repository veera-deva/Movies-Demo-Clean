package com.demo.data.repository.movies

import com.demo.domain.model.NetworkResult
import com.demo.domain.entity.MovieEntity

/**
 * Data source interface for movies API
 * */
fun interface MovieDataSource {
    suspend fun getMovies(): NetworkResult<List<MovieEntity>>
}