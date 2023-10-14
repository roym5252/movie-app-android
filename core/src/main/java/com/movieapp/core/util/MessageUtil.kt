package com.movieapp.core.util

import android.content.Context
import com.movieapp.core.R
import javax.inject.Inject

/**
 * Class for holding all general message related logic.
 */
class MessageUtil @Inject constructor(){

    fun getMessage(
        context: Context,
        errorSection: Enum<ErrorSection>,
        errorType: ErrorType
    ): Pair<String, String>? {

        when (errorSection.name) {

            "EMAIL" -> {

                when (errorType.name) {

                    "EMPTY" -> {
                        return Pair(
                            context.resources.getString(R.string.email_empty_title),
                            context.resources.getString(R.string.email_empty_desc)
                        )
                    }

                    "INVALID" -> {
                        return Pair(
                            context.resources.getString(R.string.email_invalid_title),
                            context.resources.getString(R.string.email_invalid_desc)
                        )
                    }
                }
            }

            "PASSWORD" -> {

                when (errorType.name) {

                    "EMPTY" -> {

                        return Pair(
                            context.resources.getString(R.string.password_empty_title),
                            context.resources.getString(R.string.password_empty_desc)
                        )
                    }

                    "INVALID" -> {
                        return Pair(
                            context.resources.getString(R.string.password_invalid_title),
                            context.resources.getString(R.string.password_invalid_desc)
                        )
                    }


                    "PASSWORD_MISMATCH" -> {
                        return Pair(
                            context.resources.getString(R.string.password_mismatch_title),
                            context.resources.getString(R.string.password_mismatch_desc)
                        )
                    }
                }
            }

            "CONFIRM_PASSWORD" -> {

                when (errorType.name) {

                    "EMPTY" -> {
                        return Pair(
                            context.resources.getString(R.string.confirm_password_empty_title),
                            context.resources.getString(R.string.confirm_password_empty_desc)
                        )
                    }

                    "INVALID" -> {
                        return Pair(
                            context.resources.getString(R.string.confirm_password_invalid_title),
                            context.resources.getString(R.string.confirm_password_invalid_desc)
                        )
                    }
                }
            }

            "FIRST_NAME" -> {

                when (errorType.name) {

                    "EMPTY" -> {
                        return Pair(
                            context.resources.getString(R.string.first_name_empty_title),
                            context.resources.getString(R.string.first_name_empty_desc)
                        )
                    }
                }
            }

            "LAST_NAME" -> {

                when (errorType.name) {

                    "EMPTY" -> {
                        return Pair(
                            context.resources.getString(R.string.last_name_empty_title),
                            context.resources.getString(R.string.last_name_empty_desc)
                        )
                    }
                }
            }

            "NOT_IMPLEMENTED_FEATURE" -> {

                return Pair(
                    context.resources.getString(R.string.not_implemented_title),
                    context.resources.getString(R.string.not_implemented_desc)
                )
            }

            "CONNECTIVITY" -> {

                when (errorType.name) {

                    "NO_CONNECTION" -> {

                        return Pair(
                            context.resources.getString(R.string.no_internet_title),
                            context.resources.getString(R.string.no_internet_desc)
                        )

                    }
                }

            }


            "USER" -> {

                when (errorType.name) {

                    "NOT_FOUND" -> {
                        return Pair(
                            context.resources.getString(R.string.account_not_found),
                            context.resources.getString(R.string.account_not_found_desc)
                        )
                    }

                    "INCORRECT_PASSWORD" -> {
                        return Pair(
                            context.resources.getString(R.string.user_incorrect_password),
                            context.resources.getString(R.string.user_incorrect_password_desc)
                        )
                    }

                    "ALREADY_EXISTS" -> {
                        return Pair(
                            context.resources.getString(R.string.account_already_exists),
                            context.resources.getString(R.string.account_already_exists_desc)
                        )
                    }
                }
            }

        }

        return null

    }
}