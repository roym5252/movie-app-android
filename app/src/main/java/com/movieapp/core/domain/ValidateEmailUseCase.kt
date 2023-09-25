package com.movieapp.core.domain

import com.movieapp.core.data.validator.UserInputValidator
import com.movieapp.core.util.TaskResult
import javax.inject.Inject
import javax.inject.Named

class ValidateEmailUseCase @Inject constructor(@Named("Email") private val userInputValidator: UserInputValidator) {

    operator fun invoke(email: String): TaskResult<*> = userInputValidator.validate(email)
}