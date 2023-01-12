package com.czech.chronos.utils.states

import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.network.models.PlacePredictions

sealed class PredictionsState {
    data class Success(val data: List<PlacePredictions.Prediction?>?) : PredictionsState()
    data class Error(val message: String) : PredictionsState()
    object Loading : PredictionsState()
}
