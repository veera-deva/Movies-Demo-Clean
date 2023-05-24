package com.demo.feature.movies.movies

import app.cash.turbine.test
import com.demo.domain.entity.MovieEntity
import com.demo.domain.model.NetworkResult
import com.demo.domain.usecase.GetMoviesUseCaseImpl
import com.demo.feature.movies.utils.TestUtils
import com.demo.shared_test.MainCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    private lateinit var testObject: MoviesViewModel

    @RelaxedMockK
    private lateinit var movieUseCase: GetMoviesUseCaseImpl

    @get:Rule
    var testCoroutineRUle = MainCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        testObject = MoviesViewModel(movieUseCase)
    }

    @Test
    fun `onMoviesViewModel init validate is loading`() {
        expectThat(testObject.moviesUiState.value).isEqualTo(MoviesUIState.Loading)
    }

    @Test
    fun `onMoviesViewModel getMovies() returns list of movies`() =
        runTest {
            val movieList = TestUtils.mockedMovieList()
            testObject.moviesUiState.test {
                val firstItem = awaitItem()
                expectThat(firstItem).isEqualTo(MoviesUIState.Loading)
                coEvery { movieUseCase() }.returns(flow { emit(NetworkResult.Success(movieList)) })
                testObject.getMovies()
                val secondItem = awaitItem()
                expectThat(secondItem).isEqualTo(MoviesUIState.Success(movieList))
            }
        }

    @Test
    fun `onMoviesViewModel init validate movies list data is empty`() {
        runTest {
            val movieList = emptyList<MovieEntity>()
            testObject.moviesUiState.test {
                val firstItem = awaitItem()
                expectThat(firstItem).isEqualTo(MoviesUIState.Loading)
                coEvery { movieUseCase() }.returns(flow { emit(NetworkResult.Success(movieList)) })
                testObject.getMovies()
                val secondItem = awaitItem()
                expectThat(secondItem).isEqualTo(MoviesUIState.Success(movieList))
            }
        }
    }

    @Test
    fun `onMoviesViewModel init validate getMovies return error`() {
        runTest {
            testObject.moviesUiState.test {
                val firstItem = awaitItem()
                expectThat(firstItem).isEqualTo(MoviesUIState.Loading)
                coEvery { movieUseCase() }.returns(flow {
                    emit(
                        NetworkResult.Failure(
                            Exception(TestUtils.NETWORK_ERROR_MSG)
                        )
                    )
                })
                testObject.getMovies()
                val secondItem = awaitItem()
                expectThat(secondItem).isEqualTo(MoviesUIState.Error(TestUtils.NETWORK_ERROR_MSG))
            }
        }
    }
}