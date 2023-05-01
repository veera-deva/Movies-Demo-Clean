package com.demo.movies.movies

import android.content.Context
import android.opengl.Visibility
import android.view.View
import android.view.View.OnClickListener
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.domain.entity.MovieEntity
import com.demo.movies.R
import com.demo.movies.base.BaseFragment
import com.demo.movies.databinding.FragmentMoviesBinding
import com.demo.movies.utils.AlertDialogUtils
import com.demo.movies.utils.launchAndRepeatWithLifeCycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
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
            layoutManager = GridLayoutManager(requireContext(), 3)
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

    private fun showAlertDialog(errorMessage: String?) {
        context?.let { AlertDialogUtils.showGeneralErrorMessage(it) }
    }

    private val onMovieItemClicked: (movieEntity: MovieEntity, itemVIew: View) -> Unit =
        { movieEntity, itemVIew ->
            val bundleData =
                MoviesFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movieEntity)
            findNavController().navigate(bundleData)
        }

}