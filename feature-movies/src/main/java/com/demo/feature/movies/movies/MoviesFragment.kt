package com.demo.feature.movies.movies

import android.content.Context
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.common_ui.base.BaseFragment
import com.demo.common_ui.utils.AlertDialogUtils
import com.demo.common_ui.utils.launchAndRepeatWithLifeCycle
import com.demo.domain.entity.MovieEntity
import com.demo.feature.movies.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Movies fragment to display list of movies
 */
@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {
    private val moviesViewModel by viewModels<MoviesViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showAreYouSureDialog()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun showAreYouSureDialog() {
        AlertDialogUtils.showAlertDialog(
            requireContext(),
            message = "Are you sure, you want to exit?",
            positiveButton = "Yes",
            negativeButton = "No", positiveOnClickListener = {
                activity?.finish()
            }
        )
    }

    override fun setUpView() {
        setUpRecyclerView()
        binding.btnRetry.setOnClickListener {
            moviesViewModel.getMovies()
        }

    }

    private fun setUpRecyclerView() {
        with(binding.rvMovies) {
            layoutManager = GridLayoutManager(
                requireContext(),
                3,
                GridLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(requireContext(), GridLayoutManager.VERTICAL))
        }

    }

    override fun subscribeUI() {

        with(moviesViewModel) {
            launchAndRepeatWithLifeCycle {
                launch {
                    moviesUiState.collect { uiState ->
                        when (uiState) {
                            is MoviesViewModel.UiState.Loading -> {
                                binding.progressBar.visibility =
                                    View.VISIBLE
                            }

                            is MoviesViewModel.UiState.Success -> {
                                binding.progressBar.visibility =
                                    View.GONE
                                binding.rvMovies.adapter =
                                    MoviesAdapter(
                                        uiState.moviesList,
                                        onMovieItemClicked = onMovieItemClicked
                                    )
                            }

                            is MoviesViewModel.UiState.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.llNoDataView.visibility = View.VISIBLE
                            }

                        }
                    }
                }
            }
        }

    }

    private val onMovieItemClicked: (movieEntity: MovieEntity, itemVIew: View) -> Unit =
        { movieEntity, _ ->
            val action =
                MoviesFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movieEntity)
            findNavController().navigate(action)
        }

}