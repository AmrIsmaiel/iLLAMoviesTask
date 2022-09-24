package com.som3a.data.model


/**
 * This data class represents the fields that will be
 * moved from local module to data module to use it in the feature module.
 * */
data class MovieDTO(
    var posterPath: String?,
    var adult: Boolean?,
    var overview: String?,
    var releaseDate: String?,
    var id: Int?,
    var originalTitle: String?,
    var originalLanguage: String?,
    var title: String?,
    var backdropPath: String?,
    var popularity: Double?,
    var voteCount: Int?,
    var video: Boolean?,
    var voteAverage: Double?,
    var isLiked:Boolean?
)
