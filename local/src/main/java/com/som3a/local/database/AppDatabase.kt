package com.som3a.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.som3a.local.model.MovieLocal


/**
 * Class to create the local database
 */
@Database(
    entities = [MovieLocal::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun moviesDAo(): MoviesDAO
}