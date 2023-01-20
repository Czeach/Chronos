package com.czech.chronos.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrentTimeEntity::class], version = 1, exportSchema = true)
abstract class CurrentTimeDatabase: RoomDatabase() {

    abstract fun currentTimeDao(): CurrentTimeDao

    companion object {
        const val DATABASE = "current_time_db"
    }
}