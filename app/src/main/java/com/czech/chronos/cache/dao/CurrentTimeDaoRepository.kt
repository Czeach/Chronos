package com.czech.chronos.cache.dao

import com.czech.chronos.cache.model.CurrentTimeEntity

interface CurrentTimeDaoRepository {

    suspend fun insertCurrentTime(currentTime: CurrentTimeEntity)

    suspend fun getAllCurrentTimes(): List<CurrentTimeEntity>

    suspend fun deleteCurrentTime(id: Int)
}