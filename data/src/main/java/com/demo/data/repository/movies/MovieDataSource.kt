package com.demo.data.repository.movies

import com.demo.domain.model.NetworkResult
import com.demo.domain.entity.MovieEntity

interface MovieDataSource {
    suspend fun getMovies(): NetworkResult<List<MovieEntity>>
}