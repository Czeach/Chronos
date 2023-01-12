package com.czech.chronos.interactors.place

import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.network.models.PlacePredictions
import com.czech.chronos.utils.DataState
import kotlinx.coroutines.flow.Flow

interface PredictPlaceRepository {

    fun predictPlace(
        input: String
    ): Flow<DataState<PlacePredictions>>
}