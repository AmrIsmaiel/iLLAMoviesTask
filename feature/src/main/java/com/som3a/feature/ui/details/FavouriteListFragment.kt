package com.som3a.feature.ui.details

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
import com.som3a.base.BaseFragment
import com.som3a.feature.databinding.FragmentFavouriteListBinding
import com.som3a.feature.model.MoviesItemUiModel
import com.som3a.feature.ui.contract.FavouritesContract
import com.som3a.feature.ui.main.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteListFragment : BaseFragment<FragmentFavouriteListBinding>() {

    private val mViewModel: FavouritesListViewModel by viewModels()
    override val createLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouriteListBinding
        get() = FragmentFavouriteListBinding::inflate

    private lateinit var adapter: MovieAdapter

    private fun addToFavouriteList(moviesItemUiModel: MoviesItemUiModel) {
        mViewModel.setEvent(FavouritesContract.Action.OnAddToFavorites(moviesItemUiModel))
    }

    private fun removeFromFavouriteList(moviesItemUiModel: MoviesItemUiModel) {
        mViewModel.setEvent(FavouritesContract.Action.OnRemoveFromFavorites(moviesItemUiModel))
    }


    override fun createView(savedInstanceState: Bundle?) {
        initObservers()
        adapter =MovieAdapter(
            favouriteList = true,
            favouriteMovie = { addToFavouriteList(it) },
            unFavouriteMovie = { removeFromFavouriteList(it) }
        )
        binding.rvLocalMovies.adapter = adapter
        getMoviesList()
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
    }

    private fun getMoviesList() =
        mViewModel.setEvent(FavouritesContract.Action.GetFavouritesList)

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.uiState.collect {
                    when (val state = it.movieState) {
                        is FavouritesContract.MovieState.Idle -> {
                            binding.progressBar.isVisible = false
                            binding.emptyState.isVisible = false
                        }
                        is FavouritesContract.MovieState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.nestedScrollView.isVisible = false
                        }
                        is FavouritesContract.MovieState.ListSuccess -> {
                            binding.progressBar.isVisible = false
                            val data = state.moviesList
                            if (data.isNotEmpty()) {
                                binding.nestedScrollView.isVisible = true
                            } else {
                                binding.nestedScrollView.isVisible = false
                                binding.emptyState.isVisible = true
                            }
                            binding.listCountTextView.text = data.size.toString()
                            adapter.submitList(data)
                            adapter.notifyDataSetChanged()
                        }
                        is FavouritesContract.MovieState.AddToFavouriteSuccess -> {
                        }
                        is FavouritesContract.MovieState.RemoveFromFavouriteSuccess -> {
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.effect.collect {
                    when (it) {
                        is FavouritesContract.Event.ShowError -> {
                            binding.emptyState.isVisible = true
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}