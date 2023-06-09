package com.demo.feature.movies.movies

import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.common_ui.base.BaseFragment
import com.demo.common_ui.utils.launchAndRepeatWithLifeCycle
import com.demo.domain.entity.MovieEntity
import com.demo.feature.movies.databinding.FragmentMoviesBinding
import com.demo.feature.movies.moviedetails.MovieDetailData
import com.demo.feature.movies.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Movies fragment to display list of movies
 */
@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val moviesViewModel by viewModels<MoviesViewModel>()
    override fun setUpView() {
        subscribeUI()
        setUpRecyclerView()
        handleOnBackPress()
    }

    private fun setUpRecyclerView() {
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                Constants.MOVIE_FRAGMENT_RV_SPAN_COUNT,
                GridLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), GridLayoutManager.VERTICAL))
        }
    }

    private fun subscribeUI() {
        launchAndRepeatWithLifeCycle {
            launch {
                moviesViewModel.moviesUiState.collect { uiState ->
                    when (uiState) {
                        is MoviesUIState.Loading -> {
                            showLoading()
                        }

                        is MoviesUIState.Success -> {
                            hideLoading()
                            binding.rvMovies.adapter =
                                MoviesAdapter(
                                    uiState.moviesList,
                                    onMovieItemClicked = onMovieItemClicked
                                )
                        }

                        is MoviesUIState.Error -> {
                            loadNoDataView()
                        }
                    }
                }
            }
        }
    }

    private fun loadNoDataView() {
        hideLoading()
        binding.llNoDataView.visibility = View.VISIBLE
    }

    private val onMovieItemClicked: (movieEntity: MovieEntity, itemVIew: View) -> Unit =
        { movieEntity, _ ->
            val action =
                MoviesFragmentDirections.actionMovieFragmentToMovieDetailsFragment(
                    MovieDetailData(
                        title = movieEntity.title,
                        description = movieEntity.description, image = movieEntity.image
                    )
                )
            findNavController().navigate(action)
        }

    private fun handleOnBackPress() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}