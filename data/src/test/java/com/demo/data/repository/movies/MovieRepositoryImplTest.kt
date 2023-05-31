package com.demo.data.repository.movies

import com.demo.data.mapper.movies.DataMovieResponseToDomainMovieEntityMapper
import com.demo.data.repository.utils.MockedTestData.mockedMovieResponse
import com.demo.domain.model.NetworkResult
import com.demo.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {

    private lateinit var moviesRepository: MovieRepository

    private val testDispatcher = UnconfinedTestDispatcher()

    @MockK
    lateinit var remoteDataSource: MovieRemoteDataSource

    @RelaxedMockK
    lateinit var mapper: DataMovieResponseToDomainMovieEntityMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        moviesRepository = MovieRepositoryImpl(testDispatcher, remoteDataSource, mapper)
    }

    @Test
    fun `onMovieRepositoryImpl getMovies should return movie list`() = runTest {
        val movieResponse = mockedMovieResponse()
        coEvery { remoteDataSource.getMovies() }.returns(NetworkResult.Success(movieResponse))
        val responseFlow = moviesRepository.getMovies().toList()
        expectThat(responseFlow.isNotEmpty())
    }
}