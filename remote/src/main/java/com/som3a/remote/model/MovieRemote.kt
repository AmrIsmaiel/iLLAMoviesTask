package com.som3a.remote.model

import com.google.gson.annotations.SerializedName

/**
 * This data class [MovieRemote] represents the movie json
 * response from the API.
 * I just get some fields that we need to represent in the UI.
 *
 * I use @SerializedName to get the API field names
 * and set a readable name for it
 * */
data class MovieRemote(
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    var isLiked: Boolean? = null,
)