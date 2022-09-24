package com.som3a.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * represents the data we need to display in its
 * places
 * use it in the presentation layer
 * */
@Parcelize
data class MoviesItemUiModel(
    val id: Int,
    val adult: Boolean,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val original_language: String,
    val title: String,
    var isLiked: Boolean?,
    var backdropPath: String?,
    var voteCount: Int?,
    var video: Boolean?,
    var voteAverage: Double?,
    ) : Parcelable
