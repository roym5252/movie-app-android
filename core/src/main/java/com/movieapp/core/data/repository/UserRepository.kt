package com.movieapp.core.data.repository

import com.movieapp.core.util.TaskResult

interface UserRepository {

    suspend fun checkLoginStatus(): Boolean
    suspend fun login(email: String?, password: String?): TaskResult<Boolean>
    suspend fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): TaskResult<Boolean>

    suspend fun logout(): TaskResult<Boolean>
}