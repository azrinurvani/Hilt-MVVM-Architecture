package com.azrinurvani.latihanhiltmvvm.di

import android.content.Context
import androidx.room.Room
import com.azrinurvani.latihanhiltmvvm.data.source.local.room.NewsDatabase
import com.azrinurvani.latihanhiltmvvm.utils.NEWS_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//TODO - Step 38
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabaseBuilder(@ApplicationContext context: Context) : NewsDatabase =
        Room.databaseBuilder(
            context = context,
            klass = NewsDatabase::class.java,
            name = NEWS_DB_NAME
        ).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideHistoryDao(database: NewsDatabase)  = database.historyDao()
}