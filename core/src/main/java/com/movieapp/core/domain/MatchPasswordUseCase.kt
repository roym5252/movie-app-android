package com.movieapp.core.domain

import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.ErrorType
import com.movieapp.core.util.TaskResult
import javax.inject.Inject

class MatchPasswordUseCase  @Inject constructor() {

    operator fun invoke(password:String,confirmPassword:String): TaskResult<*>{
        val result = password.contentEquals(confirmPassword)

        return if (result){
            TaskResult.Success(true)
        }else{
            TaskResult.Error(ErrorSection.PASSWORD,ErrorType.PASSWORD_MISMATCH)
        }
    }
}