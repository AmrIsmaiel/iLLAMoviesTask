package com.som3a.feature.ui.details

import androidx.lifecycle.viewModelScope
import com.som3a.base.BaseViewModel
import com.som3a.common.Resource
import com.som3a.domain.useCase.AddToFavouriteUseCase
import com.som3a.domain.useCase.GetFavouriteListUseCase
import com.som3a.domain.useCase.RemoveFromFavouriteUseCase
import com.som3a.feature.mapper.MoviesItemUiMapper
import com.som3a.feature.model.MoviesItemUiModel
import com.som3a.feature.ui.contract.FavouritesContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This viewModel to handle all threading
 * and data we need to save during the interaction
 * with the view.
 * */
@HiltViewModel
class FavouritesListViewModel @Inject constructor(
    private val getFavouriteListUseCase: GetFavouriteListUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase,
    private val movieDetailsUiMapper: MoviesItemUiMapper
) : BaseViewModel<FavouritesContract.Action, FavouritesContract.State, FavouritesContract.Event>() {
    override fun createInitialState(): FavouritesContract.State {
        return FavouritesContract.State(
            movieState = FavouritesContract.MovieState.Idle
        )
    }

    override fun handleEvent(event: FavouritesContract.Action) {
        when (event) {
            is FavouritesContract.Action.GetFavouritesList -> {
                getFavouriteList()
            }
            is FavouritesContract.Action.OnAddToFavorites -> {
                addMovieToFavourites(event.movie)
            }
            is FavouritesContract.Action.OnRemoveFromFavorites -> {
                removeMovieFromFavourites(event.movie)
            }
        }
    }

    private fun addMovieToFavourites(movie: MoviesItemUiModel) {
        viewModelScope.launch {
            addToFavouriteUseCase.execute(movieDetailsUiMapper.reversMap(movie))
                .collect {
                    when (it) {
                        is Resource.Loading -> Unit
                        is Resource.Empty -> Unit
                        is Resource.Error -> Unit
                        is Resource.Success -> {
                            getFavouriteList()
                        }
                    }
                }
        }
    }

    private fun removeMovieFromFavourites(movie: MoviesItemUiModel) {
        viewModelScope.launch {
            removeFromFavouriteUseCase.execute(movieDetailsUiMapper.reversMap(movie))
                .collect {
                    when (it) {
                        is Resource.Loading -> Unit
                        is Resource.Empty -> Unit
                        is Resource.Error -> Unit
                        is Resource.Success -> {
                            getFavouriteList()
                        }
                    }
                }
        }
    }


    private fun getFavouriteList() =
        viewModelScope.launch {
            getFavouriteListUseCase.execute()
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(movieState = FavouritesContract.MovieState.Loading) }
                        }
                        is Resource.Empty -> {
                            setState { copy(movieState = FavouritesContract.MovieState.Idle) }
                        }
                        is Resource.Error -> {
                            setEffect { FavouritesContract.Event.ShowError(message = it.exception.message) }
                        }
                        is Resource.Success -> {
                            setState {
                                copy(
                                    movieState = FavouritesContract.MovieState.ListSuccess(
                                        moviesList = movieDetailsUiMapper.mapList(it.data)
                                    )
                                )
                            }
                        }
                    }
                }
        }
}

