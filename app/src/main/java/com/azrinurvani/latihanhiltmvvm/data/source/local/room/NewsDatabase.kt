package com.azrinurvani.latihanhiltmvvm.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.azrinurvani.latihanhiltmvvm.data.source.local.entity.HistoryEntity

//TODO - Step 37
@Database(
    entities = [ HistoryEntity::class ],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun historyDao() : HistoryDao
}