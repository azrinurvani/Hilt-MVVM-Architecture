package com.azrinurvani.latihanhiltmvvm.data

import com.azrinurvani.latihanhiltmvvm.domain.model.News
import kotlinx.coroutines.flow.Flow

//TODO - Step 15
interface NewsRepository {
    fun getMainHeadlines() : Flow<Resource<MutableList<List<News>>>>
}