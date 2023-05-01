package com.demo.domain.repository

import com.demo.domain.entity.MovieEntity
import com.demo.data.model.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(): Flow<NetworkResult<List<MovieEntity>>>
}