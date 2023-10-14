package com.movieapp.core.data.validator.impl

import com.movieapp.core.data.validator.UserInputValidator
import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.ErrorType
import com.movieapp.core.util.TaskResult
import javax.inject.Inject

internal class EmailValidator @Inject constructor(): UserInputValidator {

    override fun validate(userInput: String): TaskResult<Boolean?> {

        if (userInput.isEmpty()){
            return TaskResult.Error(ErrorSection.EMAIL, ErrorType.EMPTY)
        }else if (!isEmailValid(userInput)){
            return TaskResult.Error(ErrorSection.EMAIL, ErrorType.INVALID)
        }

        return TaskResult.Success()
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}