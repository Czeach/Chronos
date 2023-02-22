package com.czech.chronos.repositories

import com.czech.chronos.network.models.ConvertTime
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.network.models.PlacePredictions
import com.czech.chronos.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ChronosRepository {

    fun predictPlace(
        input: String
    ): Flow<DataState<PlacePredictions>>

    fun getCurrentTime(
        location: String
    ): Flow<DataState<CurrentTime>>

    fun convertTime(
        baseLocation: String,
        baseDatetime: String,
        targetLocation: String
    ): Flow<DataState<ConvertTime>>


}