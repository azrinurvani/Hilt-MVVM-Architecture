package com.azrinurvani.latihanhiltmvvm.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.azrinurvani.latihanhiltmvvm.domain.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//TODO - Step 48
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    val getAllHistory = newsUseCase.getListHistory().asLiveData()
}