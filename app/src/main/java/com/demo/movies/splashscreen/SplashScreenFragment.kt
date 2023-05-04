package com.demo.movies.splashscreen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.demo.movies.R
import com.demo.movies.base.BaseFragment
import com.demo.movies.databinding.FragmentSplashScreenBinding
import com.demo.movies.utils.launchAndRepeatWithLifeCycle
import kotlinx.coroutines.launch

class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {
    private val splashViewModel by viewModels<SplashScreenViewModel>()
    override fun setUpView() {

    }

    override fun subscribeUI() {
        with(splashViewModel) {
            launchAndRepeatWithLifeCycle {
                launch {
                    state.collect { uiState ->
                        when (uiState) {
                            is SplashScreenViewModel.SplashScreenUiState.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            is SplashScreenViewModel.SplashScreenUiState.Success -> {
                                findNavController().navigate(R.id.MoviesFragment)
                            }
                        }
                    }
                }
            }
        }
    }


}