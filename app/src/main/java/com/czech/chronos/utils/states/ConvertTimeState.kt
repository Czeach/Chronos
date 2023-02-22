package com.czech.chronos.utils.states

import com.czech.chronos.network.models.ConvertTime

sealed class ConvertTimeState {
	data class Success(val data: ConvertTime?) : ConvertTimeState()
	data class Error(val message: String) : ConvertTimeState()
	object Loading : ConvertTimeState()
}
