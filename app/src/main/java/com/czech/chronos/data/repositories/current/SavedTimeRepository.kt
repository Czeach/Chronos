package com.czech.chronos.data.repositories.current

import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.utils.DataState
import kotlinx.coroutines.flow.Flow

interface SavedTimeRepository {
	fun getSavedTime(): Flow<DataState<List<CurrentTime>>>
}