package com.movieapp.core.util

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.inputmethod.InputMethodManager
import com.movieapp.core.ui.BaseActivity
import javax.inject.Inject

/**
 * Class for holding all general common util variables and methods.
 */
class CommonUtil @Inject constructor() {

    fun dismissKeyBoard(activity: BaseActivity) {

        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?

        if (imm!!.isAcceptingText) {
            imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
        }
    }

    fun isInternetConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun checkNetworkConnectivity(
        context: Context
    ): TaskResult<Boolean> {

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return TaskResult.Error(
            ErrorSection.CONNECTIVITY,
            ErrorType.NO_CONNECTION
        )
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return TaskResult.Error(
                ErrorSection.CONNECTIVITY,
                ErrorType.NO_CONNECTION
            )
        val result = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

        return if (result) {
            TaskResult.Success()
        } else {
            TaskResult.Error(ErrorSection.CONNECTIVITY, ErrorType.NO_CONNECTION)
        }
    }
}