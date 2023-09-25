package com.movieapp.core.di

import com.movieapp.core.data.datasource.DataSourceManager
import com.movieapp.core.data.datasource.remote.APIInterface
import com.movieapp.core.data.repository.MovieRepository
import com.movieapp.core.data.repository.UserRepository
import com.movieapp.core.data.repository.impl.MovieRepositoryImpl
import com.movieapp.core.data.repository.impl.UserRepositoryImpl
import com.movieapp.core.util.PrefUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideUserRepository(
        dataSourceManager: DataSourceManager,
        prefUtil: PrefUtil,
    ): UserRepository {
        return UserRepositoryImpl(
            dataSourceManager,
            prefUtil
        )
    }

    @Provides
    fun provideMovieRepository(
        apiClient: APIInterface,
        prefUtil: PrefUtil,
    ): MovieRepository {
        return MovieRepositoryImpl(
            apiClient,
            prefUtil
        )
    }
}