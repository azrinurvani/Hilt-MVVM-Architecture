package com.azrinurvani.latihanhiltmvvm.data.source.remote

import android.util.Log
import com.azrinurvani.latihanhiltmvvm.data.source.remote.network.ApiResponse
import com.azrinurvani.latihanhiltmvvm.data.source.remote.network.ApiService
import com.azrinurvani.latihanhiltmvvm.data.source.remote.response.NewsResponse
import com.azrinurvani.latihanhiltmvvm.utils.TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject
import javax.inject.Singleton

//TODO - Step 12
@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getHeadlineNews(category : String) : Flow<ApiResponse<NewsResponse>> = flow {
        try {
            emit(
                ApiResponse.Success(
                    apiService.getHeadLineNews(
                        apiKey = TOKEN,
                        country = "us",
                        page = 1,
                        pageSize = 5,
                        category = category
                    )
                )
            )
        }catch (e : Throwable){
            emit(ApiResponse.Error(e))
            Log.e(javaClass.name, "getHeadlineNews: $e")
        }
    }
}