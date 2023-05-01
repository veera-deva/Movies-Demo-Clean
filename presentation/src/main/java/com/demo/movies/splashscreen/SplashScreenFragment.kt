package com.demo.movies.splashscreen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.demo.movies.R
import com.demo.movies.base.BaseFragment
import com.demo.movies.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {
    private val splashViewModel by viewModels<SplashScreenViewModel>()
    override fun setUpView() {

    }

    override fun subscribeUI() {
        with(splashViewModel) {
            uiState.observe(viewLifecycleOwner) {
                if (it.isCompleted) {
                    findNavController().navigate(R.id.MoviesFragment)
                }
            }
            showLoading.observe(viewLifecycleOwner) {
                binding.progressBar.visibility.run {
                    if (it) View.VISIBLE else View.GONE
                }
            }
        }
    }


}