package com.azrinurvani.latihanhiltmvvm.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azrinurvani.latihanhiltmvvm.data.source.local.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

//TODO - Step 36
@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun getAllHistoryNews() : Flow<List<HistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryNews(entity: HistoryEntity)
}