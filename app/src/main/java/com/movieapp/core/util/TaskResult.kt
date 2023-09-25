package com.movieapp.core.util

/**
 * Generic class for holding Task result.
 */
sealed class TaskResult<out T : Any?> {

    data class Success<out T : Any?>(val data: T?=null) : TaskResult<T>()
    data class Error(
        val errorSection: ErrorSection,
        val errorType: ErrorType,
        val errorMessage: String? = null,
    ) : TaskResult<Nothing>()

}