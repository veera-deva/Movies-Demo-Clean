package com.demo.data.repository.movies

import com.demo.data.api.MovieApi
import com.demo.data.base.NetworkApiCall
import com.demo.data.model.movies.MovieResponse
import com.demo.domain.model.NetworkResult
import javax.inject.Inject

/**
 * Remote data source class for movies API
 * */
class MovieRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi,
) : MovieDataSource {
    override suspend fun getMovies(): NetworkResult<List<MovieResponse>> =
        NetworkApiCall.safeApiCall { movieApi.getMovieList() }

}
