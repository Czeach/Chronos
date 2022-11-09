package com.czech.chronos.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.czech.chronos.cache.model.CurrentTimeEntity

@Dao
interface CurrentTimeDao {

    @Insert
    suspend fun insertCurrentTime(currentTime: CurrentTimeEntity): Long

    @Query("SELECT * FROM current_times WHERE id = :pk")
    suspend fun getCurrentTimeById(pk: Int): CurrentTimeEntity?

    @Query("DELETE FROM current_times WHERE id = :pk" )
    suspend fun deleteCurrentTime(pk: Int): Int
}