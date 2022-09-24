package com.som3a.local.database

import androidx.room.*
import com.som3a.local.model.MovieLocal

@Dao
interface MoviesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieItem(movie: MovieLocal): Long

    @Query("SELECT * FROM movies WHERE title = :title")
    suspend fun getMovieItemByTitle(title: String): MovieLocal

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieItemById(id: String): MovieLocal

    @Query("SELECT * FROM movies")
    suspend fun getMoviesItems(): List<MovieLocal>

    @Update
    suspend fun updateMovieItem(movie: MovieLocal): Int

    @Query("DELETE FROM movies WHERE id = :id")
    suspend fun deleteMovieItemById(id: Int): Int

    @Delete
    suspend fun deleteMovieItem(movie: MovieLocal): Int

    @Query("DELETE FROM movies")
    suspend fun clearCachedMovieItems(): Int
}