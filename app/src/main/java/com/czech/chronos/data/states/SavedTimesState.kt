package com.czech.chronos.data.states

import com.czech.chronos.network.models.CurrentTime

sealed interface SavedTimesState {
	data class Success(val data: List<CurrentTime>): SavedTimesState
	data class Error(val message: String?): SavedTimesState
}