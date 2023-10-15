package com.movieapp.util

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.movieapp.core.util.NavigationUtil
import com.movieapp.core.util.ext.navigateToFragment
import com.movieapp.feature_login.ui.LoginFragment
import com.movieapp.feature_movielisting.ui.MovieListingFragment
import com.movieapp.feature_signup.ui.SignUpFragment
import javax.inject.Inject

internal class NavigationUtilImpl @Inject constructor(): NavigationUtil {

    override fun navigateToSignUpFragment(activity: FragmentActivity,clearBackStack:Boolean) {
        activity.navigateToFragment(SignUpFragment::class.java,clearBackStack)
    }

    //For navigation to movie listing fragment.
    override fun navigateToMovieListingFragment(
        activity: FragmentActivity,
        clearBackStack: Boolean,
        bundle: Bundle?
    ) {
        activity.navigateToFragment(MovieListingFragment::class.java, clearBackStack,bundle)
    }

    //For navigation to signup fragment.
    override fun navigateToLoginFragment(activity: FragmentActivity,clearBackStack:Boolean) {
        activity.navigateToFragment(LoginFragment::class.java, clearBackStack)
    }
}