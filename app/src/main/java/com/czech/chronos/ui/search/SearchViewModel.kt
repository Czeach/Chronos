package com.czech.chronos.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.chronos.interactors.convert.CurrentTimeRepository
import com.czech.chronos.interactors.place.PredictPlaceRepository
import com.czech.chronos.network.models.PlacePredictions
import com.czech.chronos.utils.states.CurrentTimeState
import com.czech.chronos.utils.states.PredictionsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val currentTimeRepository: CurrentTimeRepository,
    private val predictPlaceRepository: PredictPlaceRepository
): ViewModel() {

    val inputState = mutableStateOf(TextFieldValue(""))
    val currentTimeState = MutableStateFlow<CurrentTimeState?>(null)
    val predictionsState = MutableStateFlow<PredictionsState?>(null)

    fun getCityPredictions(input: String) {
        viewModelScope.launch {
            predictPlaceRepository.predictPlace(input).collect {
                when {
                    it.isLoading -> {
                        predictionsState.value = PredictionsState.Loading
                    }
                    it.data == null -> {
                        predictionsState.value = PredictionsState.Error(message = it.message.toString())
                    }
                    else -> {
                        predictionsState.value = PredictionsState.Success(data = it.data.predictions)
                    }
                }
            }
        }
    }

    fun getCurrentTime(location: String) {
        viewModelScope.launch {
            currentTimeRepository.getCurrentTime(location).collect {
                when {
                    it.isLoading -> {
                        currentTimeState.value = CurrentTimeState.Loading
                    }
                    it.data == null -> {
                        currentTimeState.value = CurrentTimeState.Error(message = "")
                    }
                    else -> {
                        it.data.let { data ->
                            currentTimeState.value = CurrentTimeState.Success(data = data)
                        }
                    }
                }
            }
        }
    }
}