package com.movieapp.core.di

import android.content.Context
import androidx.room.Room
import com.movieapp.core.data.datasource.DataSourceManager
import com.movieapp.core.data.datasource.local.LocalDataSourceManagerImpl
import com.movieapp.core.data.datasource.local.database.AppDatabase
import com.movieapp.core.data.datasource.remote.APIInterface
import com.movieapp.core.util.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context:Context): AppDatabase {

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor{

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(interceptor:HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideApiClient(okHttpClient:OkHttpClient,moshi: Moshi): APIInterface {

        val apiClient = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()

        return apiClient.create(
            APIInterface::class.java
        )
    }

    @Provides
    fun provideDataSourceManager(database: AppDatabase): DataSourceManager {
        return LocalDataSourceManagerImpl(database)
    }

    @Provides
    fun provideMoshi():Moshi = Moshi.Builder().build()
}