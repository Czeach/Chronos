package com.example.chronos.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chronos.cache.CurrentTimeDao
import com.example.chronos.cache.model.CurrentTimeEntity

@Database(entities = [CurrentTimeEntity::class], version = 1)
abstract class CurrentTimeDatabase: RoomDatabase() {

    abstract fun currentTimeDao(): CurrentTimeDao

    companion object {
        val DATABASE = "current_time_db"
    }
}