package com.movieapp.core.data.datasource

import com.movieapp.core.data.model.LocalUser

/**
 * Interface which sets required methods for interacting with data source for user authentication.
 */
interface DataSourceManager {
    fun registerUser(localUser: LocalUser)
    fun authenticate(email:String,password:String):Boolean
    fun checkAccountExists(email:String):Boolean
}