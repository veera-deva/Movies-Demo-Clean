package com.demo.movies.movies

import androidx.lifecycle.viewModelScope
import com.demo.domain.model.NetworkResult
import com.demo.domain.entity.MovieEntity
import com.demo.domain.usecase.MovieUseCase
import com.demo.movies.base.BaseViewModel
import com.demo.movies.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesUseCase: MovieUseCase) :
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


    init {
        getMovies()
    }

    fun getMovies() = viewModelScope.launch {
        moviesUseCase().collect { response ->
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