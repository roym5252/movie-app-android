package com.movieapp.core.data.repository.impl

import com.movieapp.core.data.datasource.DataSourceManager
import com.movieapp.core.data.model.LocalUser
import com.movieapp.core.data.repository.UserRepository
import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.ErrorType
import com.movieapp.core.util.PREF_LOGIN_STATUS
import com.movieapp.core.util.PrefUtil
import com.movieapp.core.util.TaskResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Default implementation of user repository interface.
 */
class UserRepositoryImpl @Inject constructor(
    private val dataSourceManager: DataSourceManager,
    private val prefUtil: PrefUtil
) : UserRepository {

    //Check if user is logged in
    override suspend fun checkLoginStatus(): Boolean {

        return withContext(Dispatchers.IO) {
            return@withContext prefUtil.getBoolean(PREF_LOGIN_STATUS)
        }
    }

    //Handles user login.
    override suspend fun login(email: String?, password: String?): TaskResult<Boolean> {

        return withContext(Dispatchers.IO) {

            delay(1000)

            val accountExists = dataSourceManager.checkAccountExists(email!!)

            if (!accountExists) {
                return@withContext TaskResult.Error(ErrorSection.USER, ErrorType.NOT_FOUND)
            }

            val credentialsMatched = dataSourceManager.authenticate(email, password!!)

            if (credentialsMatched) {
                prefUtil.saveBoolean(PREF_LOGIN_STATUS, true)
                return@withContext TaskResult.Success(true)
            } else {
                return@withContext TaskResult.Error(ErrorSection.USER, ErrorType.INCORRECT_PASSWORD)
            }

        }

    }

    //Handles user logout.
    override suspend fun logout(): TaskResult<Boolean> {

        return withContext(Dispatchers.IO) {
            prefUtil.saveBoolean(PREF_LOGIN_STATUS, false)
            return@withContext TaskResult.Success(true)
        }

    }

    //Handles user registration.
    override suspend fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): TaskResult<Boolean> {

        return withContext(Dispatchers.IO) {

            delay(1000)

            val accountExists = dataSourceManager.checkAccountExists(email)

            if (accountExists) {
                return@withContext TaskResult.Error(ErrorSection.USER, ErrorType.ALREADY_EXISTS)
            }

            val localUser = LocalUser(0, firstName, lastName, email, password)
            dataSourceManager.registerUser(localUser)
            prefUtil.saveBoolean(PREF_LOGIN_STATUS, true)
            return@withContext TaskResult.Success(true)

        }

    }

}
