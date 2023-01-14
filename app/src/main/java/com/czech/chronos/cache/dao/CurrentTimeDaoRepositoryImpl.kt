package com.czech.chronos.cache.dao

import com.czech.chronos.cache.model.CurrentTimeEntity
import javax.inject.Inject

class CurrentTimeDaoRepositoryImpl @Inject constructor(
    private val currentTimeDao: CurrentTimeDao
): CurrentTimeDaoRepository {

    override suspend fun insertCurrentTime(currentTime: CurrentTimeEntity) {
        currentTimeDao.insertCurrentTime(currentTime)
    }

    override suspend fun getAllCurrentTimes(): List<CurrentTimeEntity> {
        return currentTimeDao.getAllCurrentTimes()
    }

    override suspend fun exists(location: String): Boolean {
        return currentTimeDao.exists(location)
    }

    override suspend fun deleteCurrentTime(location: String) {
        currentTimeDao.deleteCurrentTime(location)
    }
}