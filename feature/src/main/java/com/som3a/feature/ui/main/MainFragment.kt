package com.som3a.feature.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.som3a.base.BaseFragment
import com.som3a.base.EndlessRecyclerOnScrollListener
import com.som3a.common.haveNetworkConnection
import com.som3a.feature.databinding.FragmentMainBinding
import com.som3a.feature.model.MoviesItemUiModel
import com.som3a.feature.ui.contract.MainContract
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var endlessRecyclerOnScrollListener: EndlessRecyclerOnScrollListener

    private lateinit var adapter: MovieAdapter

    private fun addToFavouriteList(moviesItemUiModel: MoviesItemUiModel) {
        viewModel.setEvent(MainContract.Action.OnAddToFavorites(moviesItemUiModel))
    }

    private fun removeFromFavouriteList(moviesItemUiModel: MoviesItemUiModel) {
        viewModel.setEvent(MainContract.Action.OnRemoveFromFavorites(moviesItemUiModel))
    }

    override val createLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun createView(savedInstanceState: Bundle?) {
        initObservers()
        adapter = MovieAdapter(
            favouriteList = false,
            favouriteMovie = { addToFavouriteList(it) },
            unFavouriteMovie = { removeFromFavouriteList(it) }
        )
        binding.rvMovies.adapter = adapter
        getMoviesList()
        binding.tryAgainButton.setOnClickListener { getMoviesList() }
        endlessRecyclerOnScrollListener = object :
            EndlessRecyclerOnScrollListener(
                (binding.rvMovies.layoutManager as LinearLayoutManager)
            ) {
            override fun onLoadMore() {
                if (!viewModel.isLastPage()) {
                    viewModel.updatePage()
                    getMoviesList()
                }
            }
        }
        binding.rvMovies.setOnScrollListener(endlessRecyclerOnScrollListener)
        binding.likeListButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment()
            findNavController().navigate(action)
            viewModel.clear()
        }
    }

    private fun getMoviesList() =
        viewModel.setEvent(MainContract.Action.OnFetchMoviesList)

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (val state = it.movieState) {
                        is MainContract.MovieState.Idle -> {
                            binding.emptyState.isVisible = false
                            binding.progressBar.isVisible = false
                        }
                        is MainContract.MovieState.Loading -> {
                            binding.emptyState.isVisible = false
                            binding.progressBar.isVisible = true
                        }
                        is MainContract.MovieState.ListSuccess -> {
                            binding.progressBar.isVisible = false
                            val data = state.moviesList
                            binding.emptyState.isVisible = data.isEmpty()
                            adapter.submitList(data)
                            adapter.notifyDataSetChanged()
                        }
                        is MainContract.MovieState.AddToFavouriteSuccess -> {
                            viewModel.clear()
                            getMoviesList()
                        }
                        is MainContract.MovieState.RemoveFromFavouriteSuccess -> {
                            viewModel.clear()
                            getMoviesList()
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    when (it) {
                        is MainContract.Event.ShowError -> {
                            binding.emptyState.isVisible = true
                            if (!requireContext().haveNetworkConnection()) {
                                Toast.makeText(
                                    requireContext(),
                                    "No internet connection, please try again later",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                                    .show()
                        }
                    }
                }
            }
        }
    }
}
