package com.movieapp.core.util

import android.os.Handler
import android.os.Looper
import com.movieapp.core.R
import com.movieapp.core.ui.BaseFragment
import org.aviran.cookiebar2.CookieBar
import javax.inject.Inject

/**
 * Implementation of NotificationUtil
 */
internal class NotificationUtilImpl @Inject constructor(): NotificationUtil {

    override fun showWarningMessage(fragment: BaseFragment, title: String, message: String) {

        Handler(Looper.getMainLooper()).postDelayed({

            if (fragment.isAdded && !fragment.requireActivity().isFinishing) {

                CookieBar.build(fragment.requireActivity())
                    .setTitle(title)
                    .setMessage(message)
                    .setTitleColor(R.color.white)
                    .setIcon(R.drawable.ic_warning)
                    .setBackgroundColor(R.color.purple_500)
                    .setDuration(5000)
                    .show()
            }
        }, 100)

    }

    override fun showErrorMessage(
        fragment: BaseFragment,
        title: String,
        message: String,
        icon: Int
    ) {

        Handler(Looper.getMainLooper()).postDelayed({
            if (fragment.isAdded && !fragment.requireActivity().isFinishing) {

                CookieBar.build(fragment.requireActivity())
                    .setTitle(title)
                    .setMessage(message)
                    .setTitleColor(R.color.white)
                    .setIcon(icon)
                    .setBackgroundColor(R.color.brick_red)
                    .setDuration(5000)
                    .show()
            }
        }, 100)

    }

    override fun showSuccessMessage(fragment: BaseFragment, title: String, message: String) {

        Handler(Looper.getMainLooper()).postDelayed({

            if (fragment.isAdded && !fragment.requireActivity().isFinishing) {

                CookieBar.build(fragment.requireActivity())
                    .setTitle(title)
                    .setMessage(message)
                    .setTitleColor(R.color.white)
                    .setIcon(R.drawable.ic_verified)
                    .setBackgroundColor(R.color.green)
                    .setDuration(2000)
                    .show()
            }
        }, 100)
    }
}