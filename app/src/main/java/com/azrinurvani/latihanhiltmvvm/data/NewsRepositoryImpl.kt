package com.azrinurvani.latihanhiltmvvm.data

import com.azrinurvani.latihanhiltmvvm.data.source.local.LocalDataSource
import com.azrinurvani.latihanhiltmvvm.data.source.remote.RemoteDataSource
import com.azrinurvani.latihanhiltmvvm.data.source.remote.network.ApiResponse
import com.azrinurvani.latihanhiltmvvm.domain.model.News
import com.azrinurvani.latihanhiltmvvm.utils.Utils.listCategory
import com.azrinurvani.latihanhiltmvvm.utils.Utils.mapperDomainToEntity
import com.azrinurvani.latihanhiltmvvm.utils.Utils.mapperEntityToDomain
import com.azrinurvani.latihanhiltmvvm.utils.Utils.mapperResponseToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import javax.inject.Inject
import javax.inject.Singleton

//TODO - Step 16
@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource //TODO - Step 41
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

    //TODO - Step 42 (Implement Repository for Local Data)
    override fun getListHistory(): Flow<List<News>> = flow {
        //TODO - Step 44
        emitAll(localDataSource.getAllHistoryNews().map {
            mapperEntityToDomain(it)
        })
    }

    override suspend fun insertNewsHistory(news: News) {
        //TODO - Step 45
        localDataSource.insertHistoryNews(mapperDomainToEntity(news))
    }
}