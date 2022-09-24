package com.som3a.remote.api

import com.som3a.domain.entity.ResultWrapper
import com.som3a.remote.BuildConfig
import com.som3a.remote.model.MovieRemote
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    /**
     * This function is to call the API with the correct path and queries.
     * This api has two queries:
     * params:
     * @param page -> page number for paging
     * @param apiKey -> api key for calling the API.
     * return:
     *  @return [ResultWrapper] of [MovieRemote]
     */

    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ResultWrapper<MovieRemote>
}