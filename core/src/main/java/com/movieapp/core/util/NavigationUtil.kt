package com.movieapp.core.util

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

interface NavigationUtil {
    fun navigateToSignUpFragment(activity: FragmentActivity,clearBackStack:Boolean=false)
    fun navigateToLoginFragment(activity: FragmentActivity,clearBackStack:Boolean=true)
    fun navigateToMovieListingFragment(activity: FragmentActivity,clearBackStack:Boolean=true,bundle: Bundle?=null)
}