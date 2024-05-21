package com.azrinurvani.latihanhiltmvvm.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.azrinurvani.latihanhiltmvvm.data.Resource
import com.azrinurvani.latihanhiltmvvm.domain.NewsUseCase
import com.azrinurvani.latihanhiltmvvm.domain.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//TODO - Step 21
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel(){

    val getAllHeadlines : LiveData<Resource<MutableList<List<News>>>> = newsUseCase.getMainHeadlines().asLiveData()

}