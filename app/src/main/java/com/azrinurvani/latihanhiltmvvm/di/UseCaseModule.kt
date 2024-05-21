package com.azrinurvani.latihanhiltmvvm.di

import com.azrinurvani.latihanhiltmvvm.domain.NewsUseCase
import com.azrinurvani.latihanhiltmvvm.domain.NewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

//TODO - Step 20
@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun provideNewsUseCase(userCaseImpl: NewsUseCaseImpl) : NewsUseCase
}