package com.demo.domain.usecase

import com.demo.data.model.NetworkResult
import com.demo.domain.entity.MovieEntity
import com.demo.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<NetworkResult<List<MovieEntity>>> = movieRepository.getMovies()
}