package com.som3a.feature.ui.contract

import com.som3a.base.ViewAction
import com.som3a.base.ViewEvent
import com.som3a.base.ViewState
import com.som3a.feature.model.MoviesItemUiModel

class MainContract {

    sealed class Action : ViewAction {
        object OnFetchMoviesList : Action()
        data class OnRemoveFromFavorites(val movie: MoviesItemUiModel) : Action()
        data class OnAddToFavorites(val movie: MoviesItemUiModel) : Action()
    }

    data class State(
        val movieState: MovieState,
        val selectedMovie: MoviesItemUiModel? = null
    ) : ViewState

    sealed class MovieState {
        object Idle : MovieState()
        object Loading : MovieState()
        data class ListSuccess(val moviesList: List<MoviesItemUiModel>) : MovieState()
        object AddToFavouriteSuccess : MovieState()
        object RemoveFromFavouriteSuccess : MovieState()
    }

    sealed class Event : ViewEvent {
        data class ShowError(val message: String?) : Event()
    }
}