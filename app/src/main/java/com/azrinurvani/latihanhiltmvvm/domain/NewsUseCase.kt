package com.azrinurvani.latihanhiltmvvm.domain

import com.azrinurvani.latihanhiltmvvm.data.Resource
import com.azrinurvani.latihanhiltmvvm.data.source.local.entity.HistoryEntity
import com.azrinurvani.latihanhiltmvvm.domain.model.News
import kotlinx.coroutines.flow.Flow

//TODO - Step 18
interface NewsUseCase {

    fun getMainHeadlines() : Flow<Resource<MutableList<List<News>>>>

    //TODO - Step 46
    fun getListHistory() : Flow<List<News>>
    suspend fun insertNewsHistory(news: News)
}