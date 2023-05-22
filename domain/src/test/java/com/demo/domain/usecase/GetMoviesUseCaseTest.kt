package com.demo.domain.usecase

import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import com.demo.domain.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import strikt.api.expect

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {

    @MockK
    lateinit var userRepository: MovieRepository
    lateinit var movieUseCase: GetMoviesUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieUseCase = GetMoviesUseCaseImpl(userRepository)
    }

    @Test
    fun `onMovieUseCaseTest invoke method should return movies list`() =
        runTest {
            coEvery { movieUseCase.invoke() } returns flowOf(NetworkResult.Success(mockedMovieList()))
            val result = movieUseCase().toList()
            expect { result.isNotEmpty() }
        }


    private fun mockedMovieList() = listOf(
        MovieEntity(
            1,
            "Mock title",
            "Mock Description",
            "mockUrl", "mockCategory"
        )
    )
}