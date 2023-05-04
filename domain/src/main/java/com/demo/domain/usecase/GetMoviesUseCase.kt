package com.demo.domain.usecase

import com.demo.domain.model.NetworkResult
import com.demo.domain.entity.MovieEntity
import com.demo.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<NetworkResult<List<MovieEntity>>> = movieRepository.getMovies()
}