package com.movieapp.core.di

import com.movieapp.core.data.validator.impl.EmailValidator
import com.movieapp.core.data.validator.impl.PasswordValidator
import com.movieapp.core.data.validator.UserInputValidator
import com.movieapp.core.data.validator.impl.PersonNameValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class UserInputValidatorModule {

    @Provides
    @Named("Email")
    fun provideEmailValidator(): UserInputValidator = EmailValidator()

    @Provides
    @Named("Password")
    fun providePasswordValidator(): UserInputValidator = PasswordValidator()

    @Provides
    @Named("PersonName")
    fun providePersonNameValidator(): UserInputValidator = PersonNameValidator()
}