package com.som3a.data.mapper

import com.som3a.common.Mapper
import com.som3a.data.model.MovieDTO
import com.som3a.domain.entity.MovieEntity
import javax.inject.Inject

class MoviesDataDomainMapper @Inject constructor() : Mapper<MovieDTO, MovieEntity> {
    override fun map(input: MovieDTO): MovieEntity {
        return MovieEntity(
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

    override fun reversMap(input: MovieEntity): MovieDTO {
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
}