package com.demo.movies.movies

import android.content.Context
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.domain.entity.MovieEntity
import com.demo.movies.base.BaseFragment
import com.demo.movies.databinding.FragmentMoviesBinding
import com.demo.movies.utils.AlertDialogUtils
import com.demo.movies.utils.launchAndRepeatWithLifeCycle
import dagger.hilt.android.AndroidEntryPoint
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
            val bundleData =
                MoviesFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movieEntity)
            findNavController().navigate(bundleData)
        }

}