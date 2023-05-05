package com.demo.domain.usecase

import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    operator fun invoke(): Flow<NetworkResult<List<MovieEntity>>>
}