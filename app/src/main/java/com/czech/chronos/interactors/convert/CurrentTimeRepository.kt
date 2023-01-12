package com.czech.chronos.interactors.convert

import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.utils.DataState
import kotlinx.coroutines.flow.Flow

interface CurrentTimeRepository {

    fun getCurrentTime(
        location: String
    ): Flow<DataState<CurrentTime>>
}