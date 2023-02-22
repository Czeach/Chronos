package com.czech.chronos.repositories.convert

import com.czech.chronos.network.models.ConvertTime
import com.czech.chronos.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ConvertTimeRepository {

	fun convertTime(
		baseLocation: String,
		baseDatetime: String,
		targetLocation: String
	): Flow<DataState<ConvertTime>>

}