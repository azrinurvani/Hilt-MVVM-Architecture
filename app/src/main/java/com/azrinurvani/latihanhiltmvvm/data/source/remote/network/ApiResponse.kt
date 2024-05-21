package com.azrinurvani.latihanhiltmvvm.data.source.remote.network

//TODO - Step 11
sealed class ApiResponse<out T> private constructor(){

    data class Success<out T>(val data : T) : ApiResponse<T>()
    data class Error(val error : Throwable) : ApiResponse<Nothing>()

}