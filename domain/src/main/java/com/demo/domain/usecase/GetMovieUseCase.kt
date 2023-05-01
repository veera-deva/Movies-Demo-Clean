package com.demo.domain.usecase

import com.demo.domain.entity.MovieEntity
import kotlinx.coroutines.flow.Flow


interface GetMovieUseCase {
    operator fun invoke(): Flow<List<MovieEntity>>
}