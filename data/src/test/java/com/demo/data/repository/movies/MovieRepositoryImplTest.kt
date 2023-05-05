package com.demo.data.repository.movies

import com.demo.data.model.MovieResponse
import com.demo.data.model.toDomain
import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import com.demo.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImplTest {

    private lateinit var moviesRepository: MovieRepository

    @Mock
    lateinit var remoteDataSource: MovieRemoteDataSource

    @Before
    fun setUp() {
        moviesRepository = MovieRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `onMovieRepositoryImpl getMovies should return movie list`() = runTest {
        val movieResponse = mockedMovieResponse()
        Mockito.`when`(remoteDataSource.getMovies())
            .thenReturn(NetworkResult.Success(movieResponse))
        val responseFlow = moviesRepository.getMovies().toList()
        assert(responseFlow.isNotEmpty())
    }

    private fun mockedMovieResponse() = listOf<MovieEntity>(
        MovieResponse
            (
            id = 1,
            title = "Mock title 1",
            description = "Mock Description 1",
            image = "Mock url 1",
            category = "Mock category 1"
        ).toDomain(), MovieResponse(
            id = 2,
            title = "Mock title 2",
            description = "Mock Description 2",
            image = "Mock url 2",
            category = "Mock category 2"
        ).toDomain()
    ).toList()


}