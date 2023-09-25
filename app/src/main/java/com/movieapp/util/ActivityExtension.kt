package com.movieapp.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.movieapp.R
import com.movieapp.core.util.FragmentUtil

fun FragmentActivity.navigateToFragment(
    fragment: Class<out Fragment>,
    clearBackStack: Boolean = false,
    bundle: Bundle? = null
) {

    if (clearBackStack) {
        clearBackStack()
    }

    if (!this.isFinishing) {

        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        )
        fragmentTransaction.replace(R.id.root_container, fragment, bundle)
        fragmentTransaction.addToBackStack(fragment.name)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
    }


}

fun FragmentActivity.clearBackStack() {

    if (!this.isFinishing) {

        FragmentUtil.sDisableFragmentAnimations = true
        this.supportFragmentManager.popBackStackImmediate(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        FragmentUtil.sDisableFragmentAnimations = false
    }

}