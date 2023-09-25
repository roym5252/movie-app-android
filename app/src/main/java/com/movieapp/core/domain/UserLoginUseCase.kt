package com.movieapp.core.domain

import com.movieapp.core.data.repository.UserRepository
import com.movieapp.core.util.TaskResult
import javax.inject.Inject

class UserLoginUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(email: String?, password: String?): TaskResult<Boolean?> = userRepository.login(email,password)
}