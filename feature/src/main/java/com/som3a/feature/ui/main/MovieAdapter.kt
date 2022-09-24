package com.som3a.feature.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.som3a.base.BaseRecyclerAdapter
import com.som3a.base.BaseViewHolder
import com.som3a.feature.R
import com.som3a.feature.databinding.ItemMovieBinding
import com.som3a.feature.model.MoviesItemUiModel

class MovieAdapter constructor(
    private val favouriteList: Boolean,
    private val favouriteMovie: ((itemUiModel: MoviesItemUiModel) -> Unit)? = null,
    private val unFavouriteMovie: ((itemUiModel: MoviesItemUiModel) -> Unit)? = null
) :
    BaseRecyclerAdapter<MoviesItemUiModel, ItemMovieBinding, MovieAdapter.MovieViewHolder>(
        MovieItemDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding = binding, favouriteMovie, unFavouriteMovie)
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding,
        private val favouriteMovie: ((itemUiModel: MoviesItemUiModel) -> Unit)? = null,
        private val unFavouriteMovie: ((itemUiModel: MoviesItemUiModel) -> Unit)? = null
    ) : BaseViewHolder<MoviesItemUiModel, ItemMovieBinding>(binding) {

        override fun bind() {
            getRowItem()?.let {
                with(binding) {
                    movieImage.apply {
                        Glide.with(this)
                            .load(it.poster_path)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .error(android.R.drawable.stat_notify_error)
                            .into(this)
                    }
                    movieTitleTextView.text = it.title
                    movieReleasedYearTextView.text = it.release_date
                    likeButton.setOnClickListener { view ->
                        if (it.isLiked == true) {
                            likeButton.setImageResource(R.drawable.ic_unfavorite)
                            unFavouriteMovie?.invoke(it)
                        } else {
                            likeButton.setImageResource(R.drawable.ic_favorite)
                            favouriteMovie?.invoke(it)
                        }
                    }
                    likeButton.setImageResource(
                        if (it.isLiked == true || favouriteList)
                            R.drawable.ic_favorite
                        else
                            R.drawable.ic_unfavorite
                    )
                }
            }
        }
    }
}


class MovieItemDiffUtil : DiffUtil.ItemCallback<MoviesItemUiModel>() {
    override fun areItemsTheSame(oldItem: MoviesItemUiModel, newItem: MoviesItemUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MoviesItemUiModel,
        newItem: MoviesItemUiModel
    ): Boolean {
        return oldItem == newItem
    }
}