package com.demo.feature.movies.moviedetails

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.demo.common_ui.base.BaseFragment
import com.demo.feature.movies.databinding.FragmentMoviesDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * MovieDetailFragment to details of selected movies in movies fragment
 */
@AndroidEntryPoint
class MovieDetailFragment :
    BaseFragment<FragmentMoviesDetailsBinding>(FragmentMoviesDetailsBinding::inflate),
    MenuProvider {
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun setUpView() {
        binding.movies = args.movieData
    }

    override fun onPrepareMenu(menu: Menu) {
        menu.setGroupVisible(com.demo.common_ui.R.id.home_menu, false)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        /*Not used*/
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }
}