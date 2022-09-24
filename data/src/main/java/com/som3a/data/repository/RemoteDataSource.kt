package com.som3a.data.repository

import com.som3a.domain.entity.MovieEntity
import com.som3a.domain.entity.ResultWrapper


interface RemoteDataSource {
    suspend fun getMoviesList(page: Int): ResultWrapper<MovieEntity>
}