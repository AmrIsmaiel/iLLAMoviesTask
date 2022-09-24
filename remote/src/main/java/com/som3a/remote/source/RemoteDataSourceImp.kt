package com.som3a.remote.source

import com.som3a.data.repository.LocalDataSource
import com.som3a.data.repository.RemoteDataSource
import com.som3a.domain.entity.MovieEntity
import com.som3a.domain.entity.ResultWrapper
import com.som3a.remote.api.ApiService
import com.som3a.remote.mapper.MoviesRemoteMapper
import javax.inject.Inject

/**
 * Implementation of [RemoteDataSource] Source
 */
class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService,
    private val movieMapper: MoviesRemoteMapper,
) : RemoteDataSource {

    override suspend fun getMoviesList(page: Int): ResultWrapper<MovieEntity> {
        val networkData = apiService.getMovieList(page)
        val moviesList: MutableList<MovieEntity> = mutableListOf()
        networkData.results?.forEach {
            moviesList.add(movieMapper.map(it))
        }
        return ResultWrapper(
            page = networkData.page,
            results = moviesList,
            total_results = networkData.total_results,
            total_pages = networkData.total_pages
        )
    }

}