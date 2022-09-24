package com.som3a.remote.mapper

import com.som3a.common.Mapper
import com.som3a.domain.entity.MovieEntity
import com.som3a.remote.model.MovieRemote
import javax.inject.Inject

class MoviesRemoteMapper @Inject constructor() :
    Mapper<MovieRemote, MovieEntity> {

    override fun map(input: MovieRemote): MovieEntity {
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
            voteCount = input.voteCount,
            video = input.video,
            voteAverage = input.voteAverage,
            isLiked = input.isLiked
        )
    }

    override fun reversMap(input: MovieEntity): MovieRemote {
        return MovieRemote(
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
            voteCount = input.voteCount,
            video = input.video,
            voteAverage = input.voteAverage
        )
    }
}