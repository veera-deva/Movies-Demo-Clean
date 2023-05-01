package com.demo.movies.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.demo.data.model.NetworkResult
import com.demo.domain.entity.MovieEntity
import com.demo.domain.usecase.GetMoviesUseCaseImpl
import com.demo.movies.base.BaseViewModel
import com.demo.movies.splashscreen.SplashScreenViewModel
import com.demo.movies.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesUseCase: GetMoviesUseCaseImpl) :
    BaseViewModel() {


    sealed interface UiState {
        object Loading : UiState
        data class Success(
            val moviesList: List<MovieEntity> = emptyList()
        ) : UiState

        data class Error(val errorMessage: String? = null) : UiState
    }

    private val _moviesUiState = MutableStateFlow<UiState>(UiState.Loading)
    val moviesUiState: StateFlow<UiState> = _moviesUiState.asStateFlow()

    private val _UiStateFlow = MutableStateFlow(MovieViewState())
    val uiStateFlow: StateFlow<MovieViewState> = _UiStateFlow.asStateFlow()

    data class MovieViewState(
        val moviesList: List<MovieEntity> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    init {
        getMovies()
    }

    fun getMovies() = viewModelScope.launch {
        moviesUseCase().collect { response ->

/*            when (response) {
                is NetworkResult.Success -> {
                    _UiStateFlow.update {
                        it.copy(
                            moviesList = response.data,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }

                is NetworkResult.Failure -> {
                    _UiStateFlow.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = response.e.message
                        )
                    }
                }

                is NetworkResult.Error -> {
                    _UiStateFlow.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = Constants.DEFAULT_ERROR_MESSAGE
                        )
                    }
                }
            }*/
            when (response) {
                is NetworkResult.Success -> {
                    _moviesUiState.value = UiState.Success(response.data)
                }

                is NetworkResult.Failure -> {
                    _moviesUiState.value = UiState.Error(response.e.message)
                }

                else -> {
                    _moviesUiState.value = UiState.Error(Constants.DEFAULT_ERROR_MESSAGE)
                }
            }
        }

    }
}