package com.movieapp.core.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {

    @Named("MasterKeys")
    @Provides
    fun provideMasterKey(): String {

        val mainKeyAlias by lazy {
            val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
            MasterKeys.getOrCreate(keyGenParameterSpec)
        }

        return mainKeyAlias
    }
    @Provides
    fun provideSharedPref(
        @ApplicationContext context: Context,
        @Named("MasterKeys") mainKeyAlias: String
    ): SharedPreferences {

        val sharedPrefsFile = "sharedPrefs"

        return EncryptedSharedPreferences.create(
            sharedPrefsFile,
            mainKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}