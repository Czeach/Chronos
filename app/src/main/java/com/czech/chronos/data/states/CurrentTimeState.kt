package com.czech.chronos.data.states

import com.czech.chronos.network.models.CurrentTime

sealed interface CurrentTimeState {
    data class Success(val data: CurrentTime?) : CurrentTimeState
    data class Error(val message: String) : CurrentTimeState
}