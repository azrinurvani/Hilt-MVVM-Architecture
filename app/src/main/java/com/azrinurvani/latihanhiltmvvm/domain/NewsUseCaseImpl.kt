package com.azrinurvani.latihanhiltmvvm.domain

import com.azrinurvani.latihanhiltmvvm.data.NewsRepository
import com.azrinurvani.latihanhiltmvvm.data.Resource
import com.azrinurvani.latihanhiltmvvm.domain.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//TODO - Step 19
class NewsUseCaseImpl @Inject constructor(private val newsRepository: NewsRepository) : NewsUseCase {

    override fun getMainHeadlines(): Flow<Resource<MutableList<List<News>>>> {
        return newsRepository.getMainHeadlines()
    }
}