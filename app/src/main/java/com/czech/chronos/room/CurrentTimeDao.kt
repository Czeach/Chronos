package com.czech.chronos.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.czech.chronos.room.CurrentTimeEntity

@Dao
interface CurrentTimeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCurrentTime(currentTime: CurrentTimeEntity)

    @Query("SELECT * FROM current_times")
    suspend fun getAllCurrentTimes(): List<CurrentTimeEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM current_times WHERE requestedLocation = :location)")
    suspend fun exists(location: String): Boolean

    @Query("DELETE FROM current_times WHERE requestedLocation = :location" )
    suspend fun deleteCurrentTime(location: String)
}