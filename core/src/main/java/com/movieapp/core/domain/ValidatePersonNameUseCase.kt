package com.movieapp.core.domain

import com.movieapp.core.data.validator.UserInputValidator
import com.movieapp.core.util.TaskResult
import javax.inject.Inject
import javax.inject.Named

class ValidatePersonNameUseCase  @Inject constructor(@Named("PersonName") private val userInputValidator: UserInputValidator) {
    operator fun invoke(personName:String): TaskResult<*> = userInputValidator.validate(personName)
}