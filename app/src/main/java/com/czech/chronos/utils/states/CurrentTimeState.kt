package com.czech.chronos.utils.states

import com.czech.chronos.network.models.CurrentTime

sealed class CurrentTimeState {
    data class Success(val data: CurrentTime?) : CurrentTimeState()
    data class Error(val message: String) : CurrentTimeState()
    object Loading : CurrentTimeState()
}