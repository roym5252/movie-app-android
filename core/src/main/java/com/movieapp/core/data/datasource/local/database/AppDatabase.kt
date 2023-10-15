package com.movieapp.core.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.movieapp.core.data.datasource.local.dao.UserDao
import com.movieapp.core.data.model.LocalUser

/**
 * Database class. All db upgrade code must be handled here.
 */
@Database(entities = [LocalUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}