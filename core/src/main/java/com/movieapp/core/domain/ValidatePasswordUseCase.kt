package com.movieapp.core.domain

import com.movieapp.core.data.validator.UserInputValidator
import com.movieapp.core.util.TaskResult
import javax.inject.Inject
import javax.inject.Named

class ValidatePasswordUseCase @Inject constructor(@Named("Password") private val userInputValidator: UserInputValidator) {

    operator fun invoke(password:String): TaskResult<*> = userInputValidator.validate(password)
}