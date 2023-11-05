package com.movieapp.core.data.datasource.local

import com.movieapp.core.data.datasource.DataSourceManager
import com.movieapp.core.data.datasource.local.database.AppDatabase
import com.movieapp.core.data.model.LocalUser
import javax.inject.Inject

/**
 * Local implementation of DataSourceManager.
 * This class interacts with local database for user related records.
 */
class LocalDataSourceManagerImpl @Inject constructor(private val database: AppDatabase) :
    DataSourceManager {

    override fun registerUser(localUser: LocalUser) {
        database.userDao().insertUser(localUser)
    }

    override fun authenticate(email: String, password: String): Boolean {
        return database.userDao().checkCredentials(email, password) > 0
    }

    override fun checkAccountExists(email: String): Boolean {
        return database.userDao().checkIfUserExists(email) > 0
    }
}