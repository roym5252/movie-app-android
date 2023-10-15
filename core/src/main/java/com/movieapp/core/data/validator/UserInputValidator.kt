package com.movieapp.core.data.validator

import com.movieapp.core.util.TaskResult

interface UserInputValidator {
    fun validate(userInput:String):TaskResult<Boolean?>
}