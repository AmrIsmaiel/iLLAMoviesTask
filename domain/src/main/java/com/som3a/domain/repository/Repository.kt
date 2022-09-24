package com.som3a.domain.repository

import com.som3a.common.Resource
import com.som3a.domain.entity.MovieEntity
import com.som3a.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

/**
 * Methods of all methods we can use
 * in local and remote to be passed in every use case we
 * will create
 * We can separate this interface depend on the more features we
 * can add in the feature
 */
interface Repository {
    suspend fun getRemoteMoviesList(page: Int): Flow<Resource<ResultWrapper<MovieEntity>>>
    suspend fun getLocalMoviesList(): Flow<Resource<List<MovieEntity>>>
    suspend fun addMovieToFavorite(movie: MovieEntity): Flow<Resource<Long>>
    suspend fun removeMovieFromFavorite(movie: MovieEntity): Flow<Resource<Int>>
}