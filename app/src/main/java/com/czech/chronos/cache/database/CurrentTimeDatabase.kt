package com.czech.chronos.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.czech.chronos.cache.dao.CurrentTimeDao
import com.czech.chronos.cache.model.CurrentTimeEntity

@Database(entities = [CurrentTimeEntity::class], version = 1, exportSchema = true)
abstract class CurrentTimeDatabase: RoomDatabase() {

    abstract fun currentTimeDao(): CurrentTimeDao

    companion object {
        const val DATABASE = "current_time_db"
    }
}