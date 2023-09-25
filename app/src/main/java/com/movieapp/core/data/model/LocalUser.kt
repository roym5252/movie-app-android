package com.movieapp.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Note: Only for local storage. Should not be used outside of data layer.
 */
@Entity
data class LocalUser(

    @PrimaryKey(autoGenerate = true)
    val uid: Int=0,
    val firstName: String,
    val secondName: String,
    val emailAddress: String,
    val userPassword: String,
)