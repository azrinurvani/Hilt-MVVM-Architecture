package com.azrinurvani.latihanhiltmvvm.data.source.local

import com.azrinurvani.latihanhiltmvvm.data.source.local.entity.HistoryEntity
import com.azrinurvani.latihanhiltmvvm.data.source.local.room.HistoryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

//TODO - Step 39
@Singleton
class LocalDataSource @Inject constructor(private val historyDao: HistoryDao) {

    fun getAllHistoryNews() : Flow<List<HistoryEntity>> = historyDao.getAllHistoryNews()

    suspend fun insertHistoryNews(entity: HistoryEntity) = historyDao.insertHistoryNews(entity)
}