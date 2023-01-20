package com.czech.chronos.room.useCases

import com.czech.chronos.room.CurrentTimeEntity

interface CurrentTimeDaoUseCase {

    suspend fun insertCurrentTime(currentTime: CurrentTimeEntity)

    suspend fun getAllCurrentTimes(): List<CurrentTimeEntity>

    suspend fun exists(location: String): Boolean

    suspend fun deleteCurrentTime(location: String)
}