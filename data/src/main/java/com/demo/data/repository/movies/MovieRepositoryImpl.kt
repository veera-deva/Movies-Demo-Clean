package com.demo.data.repository.movies

import com.demo.data.di.base.IoDispatcher
import com.demo.data.mapper.movies.DataMovieResponseToDomainMovieEntityMapper
import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import com.demo.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Repository implementation class for movies API
 * */
class MovieRepositoryImpl @Inject constructor(
   @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val dataSource: MovieDataSource,
    private val movieDataMapper: DataMovieResponseToDomainMovieEntityMapper
) : MovieRepository {

    override fun getMovies(): Flow<NetworkResult<List<MovieEntity>>> {
        return flow {
            emit(dataSource.getMovies().let {
                when (it) {
                    is NetworkResult.Success -> NetworkResult.Success(movieDataMapper.map(it.data))
                    is NetworkResult.Failure -> NetworkResult.Failure(it.throwable)
                    is NetworkResult.Error -> NetworkResult.Error(it.code, it.message)
                }
            })
        }.flowOn(dispatcher)

    }
}