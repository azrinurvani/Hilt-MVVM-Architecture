package com.azrinurvani.latihanhiltmvvm.data

import com.azrinurvani.latihanhiltmvvm.data.source.remote.RemoteDataSource
import com.azrinurvani.latihanhiltmvvm.data.source.remote.network.ApiResponse
import com.azrinurvani.latihanhiltmvvm.domain.model.News
import com.azrinurvani.latihanhiltmvvm.utils.Utils.listCategory
import com.azrinurvani.latihanhiltmvvm.utils.Utils.mapperResponseToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import javax.inject.Inject
import javax.inject.Singleton

//TODO - Step 16
@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : NewsRepository{

    override fun getMainHeadlines(): Flow<Resource<MutableList<List<News>>>> = flow {
        val listCategoryNews : MutableList<List<News>> = mutableListOf()
        listCategoryNews.clear()
        emit(Resource.Loading())
        try {
            listCategory.forEach{
                when(val response = remoteDataSource.getHeadlineNews(it).single()){
                    is ApiResponse.Error ->{
                        emit(Resource.Error(message = response.error.toString()))
                    }
                    is ApiResponse.Success -> {
                        mapperResponseToDomain(response.data).let(listCategoryNews::add)
                    }
                }
            }
            emit(Resource.Success(data = listCategoryNews))
        }catch (e : Throwable){
            emit(Resource.Error( message = e.message.toString()))
        }
    }
}