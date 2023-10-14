package com.movieapp.core.data.validator.impl

import com.movieapp.core.data.validator.UserInputValidator
import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.ErrorType
import com.movieapp.core.util.TaskResult
import javax.inject.Inject

class PersonNameValidator @Inject constructor(): UserInputValidator {

    override fun validate(userInput: String): TaskResult<Boolean?> {

        if (userInput.isEmpty()){
            return TaskResult.Error(ErrorSection.PERSON_NAME, ErrorType.EMPTY)
        }

        return TaskResult.Success()
    }
}