package com.som3a.domain.entity

/**
 * This data class is a middle way between the
 * remote and the data modules
 * used in mapping between remote classes and data classes
 */
data class MovieEntity(
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
    var isLiked: Boolean?
)