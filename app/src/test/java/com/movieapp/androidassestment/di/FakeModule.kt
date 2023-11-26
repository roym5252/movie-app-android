package com.movieapp.androidassestment.di

import android.content.Context
import android.content.SharedPreferences
import com.movieapp.core.di.SharedPreferenceModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.mockito.Mock
import org.mockito.Mockito
import javax.inject.Named

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [SharedPreferenceModule::class])
class FakeModule {

    @Named("MasterKeys")
    @Provides
    fun provideMasterKey(): String {
        return Mockito.mock("")
    }

    @Provides
    fun provideSharedPref(
    ): SharedPreferences {
        return Mockito.mock(SharedPreferences::class.java)
    }
}