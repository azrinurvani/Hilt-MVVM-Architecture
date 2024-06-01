package com.azrinurvani.latihanhiltmvvm.di

import com.azrinurvani.latihanhiltmvvm.data.NewsRepository
import com.azrinurvani.latihanhiltmvvm.data.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//TODO - Step 17
@Module(
    includes = [
        NetworkModule::class,
        //TODO - Step 58
        DatabaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(newsRepository: NewsRepositoryImpl) : NewsRepository
}