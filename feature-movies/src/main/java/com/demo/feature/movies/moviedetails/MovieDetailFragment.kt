package com.demo.feature.movies.moviedetails

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.demo.common_ui.base.BaseFragment
import com.demo.feature.movies.databinding.FragmentMoviesDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * MovieDetailFragment to details of selected movies in movies fragment
 */
@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMoviesDetailsBinding>(FragmentMoviesDetailsBinding::inflate) {
    private val args: MovieDetailFragmentArgs by navArgs()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun setUpView() {
        getAndBindArgumentData()
    }

    private fun getAndBindArgumentData() {
        binding.movies = args.movieData
    }

    override fun subscribeUI() {
        /* un used*/
    }


}