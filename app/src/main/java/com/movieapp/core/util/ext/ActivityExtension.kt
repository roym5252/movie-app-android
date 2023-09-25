package com.movieapp.core.util.ext

import android.app.Activity
import androidx.core.view.WindowInsetsControllerCompat
import com.movieapp.R

fun Activity.makeSystemStatusBarTransparent() {

    this.let {

        if (!it.isFinishing) {

            val window = this.window
            val decorView = window.decorView
            val wic = WindowInsetsControllerCompat(window, decorView)
            wic.isAppearanceLightStatusBars = false
            window.statusBarColor = this.resources.getColor(R.color.colorPrimary)
        }

    }

}
