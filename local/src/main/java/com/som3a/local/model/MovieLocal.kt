package com.som3a.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * data class represents all the fields of the model
 * that saved in the database
 * this represents an item in the database
 * */
@Entity(tableName = "movies")
data class MovieLocal(
    @PrimaryKey
    var id: Int,
    var posterPath: String?,
    var adult: Boolean?,
    var overview: String?,
    var releaseDate: String?,
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
