package com.azrinurvani.latihanhiltmvvm.data

//TODO - Step 14
sealed class Resource<out T>(val data : T?, val message : String? = null) {

    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(data: T?  = null, message: String?): Resource<T>(data,message)
}