package com.movieapp

import android.app.Application
import com.movieapp.core.util.PrefUtil
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MovieApp : Application(){

    @Inject
    lateinit var prefUtil: PrefUtil

    companion object {

        init {
            System.loadLibrary("keys")
        }
    }

    private external fun getApiKey(): String

    override fun onCreate() {
        super.onCreate()

        prefUtil.saveString("api_key",getApiKey())
    }
}