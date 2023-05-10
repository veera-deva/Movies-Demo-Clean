package com.demo.movies.splashscreen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.demo.movies.R
import com.demo.common_ui.base.BaseFragment
import com.demo.movies.databinding.FragmentSplashScreenBinding
import com.demo.common_ui.utils.launchAndRepeatWithLifeCycle
import kotlinx.coroutines.launch

class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {
    private val splashViewModel by viewModels<SplashScreenViewModel>()
    override fun setUpView() {
        subscribeUI()
    }

    private fun subscribeUI() {
        with(splashViewModel) {
            launchAndRepeatWithLifeCycle {
                launch {
                    state.collect { uiState ->
                        when (uiState) {
                            is SplashScreenViewModel.SplashScreenUiState.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            is SplashScreenViewModel.SplashScreenUiState.Success -> {
                                findNavController().navigate(com.demo.feature.movies.R.id.nav_movies)
                            }
                        }
                    }
                }
            }
        }
    }


}