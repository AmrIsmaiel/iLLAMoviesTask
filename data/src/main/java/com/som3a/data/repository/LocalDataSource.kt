package com.som3a.data.repository

import com.som3a.data.model.MovieDTO

/**
 * Methods of Local Data Source
 */
interface LocalDataSource {

    suspend fun addItem(movie: MovieDTO): Long

    suspend fun getFavoriteMoviesItems(): List<MovieDTO>

    suspend fun updateItem(movie: MovieDTO): Int

    suspend fun deleteItemById(id: Int): Int

    suspend fun deleteItem(movie: MovieDTO): Int

    suspend fun clearCachedItems(): Int
}