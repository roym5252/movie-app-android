package com.movieapp.core.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Named

/**
 * Class for handling shared preference operations.
 */
class PrefUtil @Inject constructor(@ApplicationContext context: Context, private val sharedPref:SharedPreferences) {

    /*private val mainKeyAlias by lazy {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        MasterKeys.getOrCreate(keyGenParameterSpec)
    }*/



    //For saving boolean value to shared preference.
    fun saveBoolean(key: String, value: Boolean) {

        with(sharedPref.edit()) {
            putBoolean(key, value)
            apply()
        }

    }

    //For retrieving boolean value to shared preference.
    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun saveString(key: String, value: String) {

        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getString(key: String): String {
        return sharedPref.getString(key,"")?:""
    }
}