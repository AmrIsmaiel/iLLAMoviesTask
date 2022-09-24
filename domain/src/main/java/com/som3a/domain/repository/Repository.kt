package com.som3a.domain.repository

import com.som3a.common.Resource
import com.som3a.domain.entity.MovieEntity
import com.som3a.domain.entity.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getRemoteMoviesList(page: Int): Flow<Resource<ResultWrapper<MovieEntity>>>
    suspend fun getLocalMoviesList(): Flow<Resource<List<MovieEntity>>>
    suspend fun addMovieToFavorite(movie: MovieEntity): Flow<Resource<Long>>
    suspend fun removeMovieFromFavorite(movie: MovieEntity): Flow<Resource<Int>>
}