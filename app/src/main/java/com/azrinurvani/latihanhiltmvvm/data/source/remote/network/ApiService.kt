package com.azrinurvani.latihanhiltmvvm.data.source.remote.network

import com.azrinurvani.latihanhiltmvvm.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

//TODO - Step 10
interface ApiService {

    @GET("/v2/top-headlines")
    suspend fun getHeadLineNews(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("category") category: String,
    ): NewsResponse

    @GET("/v2/everything")
    suspend fun getSearchNews(
        @Query("apiKey") apiKey: String,
        @Query("q") query: String,
    ): NewsResponse
}