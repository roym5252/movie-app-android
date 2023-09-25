package com.movieapp.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.movieapp.R
import com.movieapp.core.util.ext.makeSystemStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

/**
 * Base activity class for holding general methods.
 * The child classes will get access to methods.
 */
@AndroidEntryPoint
open class BaseActivity:AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        makeSystemStatusBarTransparent()
    }


}