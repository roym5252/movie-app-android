package com.movieapp.core.model

import com.movieapp.core.data.model.LocalUser

data class User(
    val uid: Int = 0,
    val firstName: String,
    val secondName: String,
    val emailAddress: String,
    val userPassword: String,
){
    companion object{

        fun toLocalUser(user: User): LocalUser {

            return LocalUser(
                user.uid,
                user.firstName,
                user.secondName,
                user.emailAddress,
                user.userPassword
            )
        }

        fun fromLocalUser(localUser: LocalUser): User {

            return User(
                localUser.uid,
                localUser.firstName,
                localUser.secondName,
                localUser.emailAddress,
                localUser.userPassword
            )
        }
    }
}

