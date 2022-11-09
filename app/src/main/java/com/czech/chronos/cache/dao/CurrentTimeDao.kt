package com.czech.chronos.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.czech.chronos.cache.model.CurrentTimeEntity

@Dao
interface CurrentTimeDao {

    @Insert
    suspend fun insertCurrentTime(currentTime: CurrentTimeEntity)

    @Query("SELECT * FROM current_times")
    suspend fun getAllCurrentTimes(): List<CurrentTimeEntity>

    @Query("DELETE FROM current_times WHERE id = :id" )
    suspend fun deleteCurrentTime(id: Int)
}