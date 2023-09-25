package com.movieapp.core.domain

import com.movieapp.core.data.repository.UserRepository
import com.movieapp.core.util.TaskResult
import javax.inject.Inject

class UserRegistrationUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(firstName:String,lastName:String,email:String,password:String): TaskResult<Boolean> = userRepository.signUp(firstName,lastName,email,password)
}