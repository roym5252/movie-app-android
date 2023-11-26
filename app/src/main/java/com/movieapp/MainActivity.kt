package com.movieapp

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.movieapp.core.ui.BaseActivity
import com.movieapp.core.util.NavigationUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main activity.
 * This activity holds all the fragments.
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var navigationUtil: NavigationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        setLoginStatusObserver()
    }

    //For observing login status.
    private fun setLoginStatusObserver() {

        mainActivityViewModel.loginStatus.observe(this) {

            if (it) {
                navigationUtil.navigateToMovieListingFragment(this, true)
            } else {
                navigationUtil.navigateToLoginFragment(this, true)
            }
        }

    }

}
