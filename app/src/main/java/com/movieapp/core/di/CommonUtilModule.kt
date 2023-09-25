package com.movieapp.core.di

import com.movieapp.core.util.NotificationUtil
import com.movieapp.core.util.NotificationUtilImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class CommonUtilModule() {

    @Provides
    fun provideNotificationUtil(): NotificationUtil {
        return NotificationUtilImpl()
    }
}