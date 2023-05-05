package com.demo.domain.repository

import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeRepository : MovieRepository {
    private val fakeFlow = MutableSharedFlow<NetworkResult<List<MovieEntity>>>()
    suspend fun emit(value: NetworkResult<List<MovieEntity>>) = fakeFlow.emit(value)
    override fun getMovies(): Flow<NetworkResult<List<MovieEntity>>> = fakeFlow
}