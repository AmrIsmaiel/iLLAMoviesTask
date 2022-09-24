package com.som3a.data.mapper

import com.som3a.common.Mapper
import com.som3a.data.model.MovieDTO
import com.som3a.domain.entity.MovieEntity
import javax.inject.Inject


/**
 * This mapper is used to map between the data and the domain layer
 * compare between data classes if there are any missing values or null
 * to handle it here
 * This mapper is make sure the data is correct between the local datasource
 * and the use it in the feature module
 * */
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