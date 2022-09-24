package com.som3a.local.source

import com.som3a.data.model.MovieDTO
import com.som3a.data.repository.LocalDataSource
import com.som3a.local.database.MoviesDAO
import com.som3a.local.mapper.MovieLocalDataMapper
import javax.inject.Inject

/**
 * Implementation of [LocalDataSource] Source
 */
class LocalDataSourceImp @Inject constructor(
    private val moviesDAO: MoviesDAO,
    private val mapper: MovieLocalDataMapper
) : LocalDataSource {
    override suspend fun addItem(movie: MovieDTO): Long {
        val movieLocal = mapper.reversMap(movie).apply { isLiked = true }
        return moviesDAO.addMovieItem(movie = movieLocal)
    }

    override suspend fun getFavoriteMoviesItems(): List<MovieDTO> {
        val list = moviesDAO.getMoviesItems()
        return mapper.mapList(list)
    }

    override suspend fun updateItem(movie: MovieDTO): Int {
        val movieLocal = mapper.reversMap(movie)
        return moviesDAO.updateMovieItem(movieLocal)
    }

    override suspend fun deleteItemById(id: Int): Int {
        return moviesDAO.deleteMovieItemById(id)
    }

    override suspend fun deleteItem(movie: MovieDTO): Int {
        val movieLocal = mapper.reversMap(movie)
        return moviesDAO.deleteMovieItemById(movieLocal.id)
    }

    override suspend fun clearCachedItems(): Int {
        return moviesDAO.clearCachedMovieItems()
    }
}