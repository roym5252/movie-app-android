package com.movieapp.di

import com.movieapp.core.util.NavigationUtil
import com.movieapp.util.NavigationUtilImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NavigationUtilModule () {

    @Provides
    fun provideNavigationUtilModule(): NavigationUtil {
        return NavigationUtilImpl()
    }
}