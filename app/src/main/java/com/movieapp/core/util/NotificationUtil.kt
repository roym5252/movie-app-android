package com.movieapp.core.util

import com.movieapp.R
import com.movieapp.core.ui.BaseFragment

/**
 * Interface which defines the methods for displaying different types of messages.
 */
interface NotificationUtil {

    fun showWarningMessage(fragment: BaseFragment, title: String, message: String)
    fun showErrorMessage(
        fragment: BaseFragment,
        title: String,
        message: String,
        icon: Int = R.drawable.ic_warning
    )

    fun showSuccessMessage(fragment: BaseFragment, title: String, message: String)
}