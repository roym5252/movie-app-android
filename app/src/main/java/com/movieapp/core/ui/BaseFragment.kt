package com.movieapp.core.ui

import android.os.Bundle
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import com.movieapp.R
import com.movieapp.core.util.ErrorSection
import com.movieapp.core.util.ErrorType
import com.movieapp.core.util.FragmentUtil
import com.movieapp.core.util.MessageUtil
import com.movieapp.core.util.NotificationUtil
import com.movieapp.core.util.TaskResult

/**
 * Base fragment class for holding general methods.
 * The child classes will get access to methods.
 */

open class BaseFragment: Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (FragmentUtil.sDisableFragmentAnimations) {
            val animation: Animation = object : Animation() {}
            animation.duration = 0
            return animation
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    //For showing warning message.
    fun showWarningMessage(messageUtil: MessageUtil, notificationUtil: NotificationUtil, result: TaskResult.Error) {

        val message = messageUtil.getMessage(requireActivity(), result.errorSection, result.errorType)

        message?.let {
            notificationUtil.showWarningMessage(
                this,
                it.first,
                it.second
            )
        }
    }

    //For showing warning message.
    fun showWarningMessage(messageUtil: MessageUtil, notificationUtil: NotificationUtil, errorSection:ErrorSection,errorType:ErrorType) {

        val message = messageUtil.getMessage(requireActivity(), errorSection, errorType)

        message?.let {
            notificationUtil.showWarningMessage(
                this,
                it.first,
                it.second
            )
        }
    }

    //For showing success message.
    fun showSuccessMessage(title:String,message:String, notificationUtil: NotificationUtil) {

        message.let {
            notificationUtil.showSuccessMessage(
                this,
                title,
                message
            )
        }
    }

    //For showing no connectivity message.
    fun showNoConnectivityMessage(messageUtil: MessageUtil, notificationUtil: NotificationUtil) {

        val message = messageUtil.getMessage(
            requireActivity(),
            ErrorSection.CONNECTIVITY,
            ErrorType.NO_CONNECTION
        )

        message?.let {

            notificationUtil.showErrorMessage(
                this,
                it.first,
                it.second,
                R.drawable.ic_no_internet
            )
        }
    }

}