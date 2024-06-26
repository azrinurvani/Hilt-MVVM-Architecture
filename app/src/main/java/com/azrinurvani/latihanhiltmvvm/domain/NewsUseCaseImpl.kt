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

    //TODO - Step 47
    override fun getListHistory(): Flow<List<News>> {
        return newsRepository.getListHistory()
    }
    override suspend fun insertNewsHistory(news: News) {
        return newsRepository.insertNewsHistory(news)
    }

    //TODO - Step 61
    override fun getSearchNews(query: String): Flow<Resource<List<News>>> {
        return newsRepository.getSearchNews(query)
    }
}