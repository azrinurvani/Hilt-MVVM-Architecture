package com.azrinurvani.latihanhiltmvvm.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.azrinurvani.latihanhiltmvvm.data.Resource
import com.azrinurvani.latihanhiltmvvm.domain.NewsUseCase
import com.azrinurvani.latihanhiltmvvm.domain.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO - Step 21
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel(){

    val getAllHeadlines : LiveData<Resource<MutableList<List<News>>>> = newsUseCase.getMainHeadlines().asLiveData()

    //TODO - Step 54
    fun insertHistory(news: News) = viewModelScope.launch {
        newsUseCase.insertNewsHistory(news)
    }

    //TODO - Step 62
    fun getNewsSearch(query:String) = newsUseCase.getSearchNews(query).asLiveData()
}