package com.movieapp.core.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.movieapp.core.data.model.LocalUser

/**
 * Dao for handling user entity related operations.
 */
@Dao
interface UserDao {

    @Insert
    fun insertUser(localUser: LocalUser)

    //Check login credentials.
    @Query("select COUNT(*) from LocalUser where emailAddress=:email and userPassword=:password")
    fun checkCredentials(email:String,password:String):Int

    //Check if any account associated with email is available.
    @Query("select COUNT(*) from LocalUser where emailAddress=:email")
    fun checkIfUserExists(email:String):Int
}