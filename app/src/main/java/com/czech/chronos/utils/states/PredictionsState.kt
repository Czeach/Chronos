package com.czech.chronos.utils.states

import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.network.models.PlacePredictions

sealed class PredictionsState {
    data class Success(val data: List<PlacePredictions.Prediction?>?) : PredictionsState()
    data class Error(val message: String) : PredictionsState()
    object Loading : PredictionsState()
}

sealed class HomePredictionsState {
    data class Success(val data: List<PlacePredictions.Prediction?>?) : HomePredictionsState()
    data class Error(val message: String) : HomePredictionsState()
    object Loading : HomePredictionsState()
}

sealed class TargetPredictionsState {
    data class Success(val data: List<PlacePredictions.Prediction?>?) : TargetPredictionsState()
    data class Error(val message: String) : TargetPredictionsState()
    object Loading : TargetPredictionsState()
}
