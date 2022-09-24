package com.som3a.feature.ui.main

import androidx.lifecycle.viewModelScope
import com.som3a.base.BaseViewModel
import com.som3a.common.Resource
import com.som3a.common.addIfNotExist
import com.som3a.domain.useCase.AddToFavouriteUseCase
import com.som3a.domain.useCase.GetMoviesListUseCase
import com.som3a.domain.useCase.RemoveFromFavouriteUseCase
import com.som3a.feature.mapper.MoviesItemUiMapper
import com.som3a.feature.model.MoviesItemUiModel
import com.som3a.feature.ui.contract.MainContract
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
class MainViewModel @Inject constructor(
    private val getMoviesListUseCase: GetMoviesListUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase,
    private val movieMapper: MoviesItemUiMapper
) : BaseViewModel<MainContract.Action, MainContract.State, MainContract.Event>() {

    private val listOfMovies: MutableList<MoviesItemUiModel> = mutableListOf()
    var page = 1
    var pageCount = 0

    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            movieState = MainContract.MovieState.Idle,
            selectedMovie = null
        )
    }

    override fun handleEvent(event: MainContract.Action) {
        when (event) {
            is MainContract.Action.OnFetchMoviesList -> fetchMoviesList()
            is MainContract.Action.OnAddToFavorites -> {
                addMovieToFavourites(event.movie)
            }
            is MainContract.Action.OnRemoveFromFavorites -> {
                removeMovieFromFavourites(event.movie)
            }
        }
    }

    private fun addMovieToFavourites(movie: MoviesItemUiModel) {
        viewModelScope.launch {
            addToFavouriteUseCase.execute(movieMapper.reversMap(movie))
                .collect {
                    when (it) {
                        is Resource.Loading -> Unit
                        is Resource.Empty -> Unit
                        is Resource.Error -> Unit
                        is Resource.Success -> {
                            setState {
                                copy(
                                    movieState = MainContract.MovieState.AddToFavouriteSuccess
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun removeMovieFromFavourites(movie: MoviesItemUiModel) {
        viewModelScope.launch {
            removeFromFavouriteUseCase.execute(movieMapper.reversMap(movie))
                .collect {
                    when (it) {
                        is Resource.Loading -> Unit
                        is Resource.Empty -> Unit
                        is Resource.Error -> Unit
                        is Resource.Success -> {
                            setState {
                                copy(
                                    movieState = MainContract.MovieState.RemoveFromFavouriteSuccess
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun fetchMoviesList() =
        viewModelScope.launch {
            getMoviesListUseCase.execute(
                type = page
            )
                .onStart { emit(Resource.Loading) }
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            setState { copy(movieState = MainContract.MovieState.Loading) }
                        }
                        is Resource.Empty -> {
                            setState { copy(movieState = MainContract.MovieState.Idle) }
                        }
                        is Resource.Error -> {
                            setEffect { MainContract.Event.ShowError(message = it.exception.message) }
                        }
                        is Resource.Success -> {
                            if (it.data.results != null && it.data.results?.isNotEmpty() == true) {
                                val movies = movieMapper.mapList(it.data.results!!)
                                checkIfExists(movies)
                                if (it.data.total_pages != null)
                                    pageCount = it.data.total_pages!!
                            }
                            setState {
                                copy(
                                    movieState = MainContract.MovieState.ListSuccess(
                                        moviesList = listOfMovies
                                    )
                                )
                            }
                        }
                    }
                }
        }

    fun isLastPage(): Boolean {
        return page > pageCount
    }

    fun updatePage() {
        page += 1
    }

    private fun checkIfExists(list: List<MoviesItemUiModel>?) {
        if (listOfMovies.isNotEmpty()) {
            list?.forEach { item ->
                listOfMovies.addIfNotExist(item)
            }
        } else {
            if (list != null) {
                listOfMovies.addAll(list)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        page = 1
        clearList()
    }

    fun clear() {
        onCleared()
    }

    fun clearList() {
        listOfMovies.clear()
    }
}