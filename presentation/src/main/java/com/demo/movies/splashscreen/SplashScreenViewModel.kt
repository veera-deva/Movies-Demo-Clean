package com.demo.movies.splashscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class SplashScreenViewModel : ViewModel() {

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: MutableLiveData<Boolean> = _showLoading
    private val _uiState = MutableLiveData(UIState())
    val uiState: MutableLiveData<UIState> = _uiState

    data class UIState(val isCompleted: Boolean = true, val showErrorMsg: String? = null)

    init {
        load()
    }

    /*Method to show splashscreen for few milliseconds*/
    private fun load(timeMillis: Long = SPLASH_TIME) {
        _showLoading.value = true
        viewModelScope.launch {
            delay(timeMillis = timeMillis)
            _showLoading.value = false
            _uiState.value = UIState(isCompleted = false, null)
        }

    }

    companion object {
        const val SPLASH_TIME = 800L
    }
}