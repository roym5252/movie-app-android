package com.movieapp.core.util

/**
 * File for holding common constant values.
 */
enum class ErrorSection {
    EMAIL, PERSON_NAME, FIRST_NAME,LAST_NAME, PASSWORD,CONFIRM_PASSWORD,NOT_IMPLEMENTED_FEATURE,CONNECTIVITY,USER
}

enum class ErrorType {
    EMPTY, INVALID,NOT_IMPLEMENTED_FEATURE,NO_CONNECTION,PASSWORD_MISMATCH,NOT_FOUND,INCORRECT_PASSWORD,ALREADY_EXISTS
}

const val PREF_LOGIN_STATUS ="login_status"
const val API_KEY ="9cf88a8f"
const val BASE_URL ="https://www.omdbapi.com/"