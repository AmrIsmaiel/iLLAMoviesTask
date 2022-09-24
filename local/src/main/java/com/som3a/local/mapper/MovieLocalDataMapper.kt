package com.som3a.local.mapper

import com.som3a.common.Mapper
import com.som3a.data.model.MovieDTO
import com.som3a.local.model.MovieLocal
import javax.inject.Inject

class MovieLocalDataMapper @Inject constructor() : Mapper<MovieLocal, MovieDTO> {
    override fun map(input: MovieLocal): MovieDTO {
        return MovieDTO(
            posterPath = input.posterPath,
            adult = input.adult,
            overview = input.overview,
            releaseDate = input.releaseDate,
            id = input.id,
            originalTitle = input.originalTitle,
            originalLanguage = input.originalLanguage,
            title = input.title,
            backdropPath = input.backdropPath,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            video = input.video,
            isLiked = input.isLiked
        )
    }

    override fun reversMap(input: MovieDTO): MovieLocal {
        return MovieLocal(
            posterPath = input.posterPath,
            adult = input.adult,
            overview = input.overview,
            releaseDate = input.releaseDate,
            id = input.id ?: 0,
            originalTitle = input.originalTitle,
            originalLanguage = input.originalLanguage,
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