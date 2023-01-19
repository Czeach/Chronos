package com.czech.chronos.room.dao

import com.czech.chronos.room.CurrentTimeEntity
import javax.inject.Inject

class CurrentTimeDaoUseCaseImpl @Inject constructor(
    private val currentTimeDao: CurrentTimeDao
): CurrentTimeDaoUseCase {

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