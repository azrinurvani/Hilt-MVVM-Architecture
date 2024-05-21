package com.azrinurvani.latihanhiltmvvm.di

import android.util.Log
import com.azrinurvani.latihanhiltmvvm.data.source.remote.network.ApiService
import com.azrinurvani.latihanhiltmvvm.utils.BASE_URL
import com.azrinurvani.latihanhiltmvvm.utils.CALL_TIME_OUT
import com.azrinurvani.latihanhiltmvvm.utils.CONNECT_TIME_OUT
import com.azrinurvani.latihanhiltmvvm.utils.READ_TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//TODO - Step 7
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor{ message ->
            Log.d("API-LOG", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY

        }
        return interceptor
    }

    @Provides
    @Singleton
    fun provideHttpClient(logInterceptor: HttpLoggingInterceptor) : OkHttpClient{
        return OkHttpClient.Builder()
            .addNetworkInterceptor(logInterceptor)
            .callTimeout(CALL_TIME_OUT,TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT,TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIME_OUT,TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofitBuilder(httpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}