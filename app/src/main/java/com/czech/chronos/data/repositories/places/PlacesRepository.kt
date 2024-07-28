package com.czech.chronos.data.repositories.places

import com.czech.chronos.network.models.PlacePredictions
import com.czech.chronos.utils.DataState
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {

	fun predictPlace(
		input: String
	): Flow<DataState<PlacePredictions>>
}