package com.som3a.feature.mapper

import com.som3a.common.Mapper
import com.som3a.domain.entity.MovieEntity
import com.som3a.feature.model.MoviesItemUiModel
import javax.inject.Inject

class MoviesItemUiMapper @Inject constructor() : Mapper<MovieEntity, MoviesItemUiModel> {
    override fun map(input: MovieEntity): MoviesItemUiModel {
        return MoviesItemUiModel(
            id = input.id ?: -1,
            adult = input.adult ?: false,
            original_title = input.originalTitle ?: "",
            overview = input.overview ?: "",
            popularity = input.popularity ?: 0.0,
            poster_path = setPosterPath(input.posterPath),
            release_date = input.releaseDate ?: "",
            original_language = input.originalLanguage ?: "",
            title = input.title ?: "",
            isLiked = input.isLiked,
            backdropPath = input.backdropPath,
            voteCount = input.voteCount,
            video = input.video,
            voteAverage = input.voteAverage
        )
    }

    private fun setPosterPath(posterPath: String?): String {
        return if (posterPath == null) ""
        else "https://image.tmdb.org/t/p/w500/$posterPath"
    }

    override fun reversMap(input: MoviesItemUiModel): MovieEntity {
        return MovieEntity(
            posterPath = setPosterPath(input.poster_path),
            adult = input.adult,
            overview = input.overview,
            releaseDate = input.release_date,
            id = input.id,
            originalTitle = input.original_title,
            originalLanguage = input.original_language,
            title = input.title,
            backdropPath = input.backdropPath,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            video = input.video,
            isLiked = input.isLiked
        )
    }
}