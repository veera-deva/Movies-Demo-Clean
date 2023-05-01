package com.demo.data.repository.movies

import com.demo.data.model.MovieResponse
import com.demo.data.model.NetworkResult
import com.demo.domain.entity.MovieEntity

interface MovieDataSource {
    suspend fun getMovies(): NetworkResult<List<MovieEntity>>
}