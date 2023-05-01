package com.demo.movies.moviedetails

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.demo.domain.entity.MovieEntity
import com.demo.movies.base.BaseFragment
import com.demo.movies.databinding.FragmentMoviesDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMoviesDetailsBinding>(FragmentMoviesDetailsBinding::inflate) {
    val args: MovieDetailFragmentArgs by navArgs()


    override fun setUpView() {
        getAndBindArgumentData()
    }

    private fun getAndBindArgumentData() {
        binding.movies = args.movieData
    }

    override fun subscribeUI() {

    }


}