package com.czech.chronos.ui.screens.search

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.chronos.room.useCases.CurrentTimeDaoUseCase
import com.czech.chronos.room.CurrentTimeEntity
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.repositories.current.CurrentTimeRepository
import com.czech.chronos.repositories.places.PlacesRepository
import com.czech.chronos.utils.states.CurrentTimeState
import com.czech.chronos.utils.states.PredictionsState
import com.czech.chronos.utils.toCurrentTimeList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val currentTimeRepository: CurrentTimeRepository,
    private val currentTimeDaoUseCase: CurrentTimeDaoUseCase
): ViewModel() {

    var inputState = mutableStateOf(TextFieldValue(""))
    val predictionsState = MutableStateFlow<PredictionsState?>(null)
    val currentTimeState = MutableStateFlow<CurrentTimeState?>(null)

    val isInDB = MutableStateFlow(false)
    val currentTimeFromDB = MutableStateFlow(listOf<CurrentTime>())

    init {
        getCurrentTimeListFromDB()
    }

    fun getCityPredictions(input: String) {
        viewModelScope.launch {
            placesRepository.predictPlace(input).collect {
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

    fun insertCurrentTimeIntoDB(currentTime: CurrentTimeEntity, checked: Boolean) {
        viewModelScope.launch {
            currentTime.checked = checked
            currentTimeDaoUseCase.insertCurrentTime(currentTime)
        }
    }

    fun deleteCurrentTimeFromDB(location: String) {
        viewModelScope.launch {
            currentTimeDaoUseCase.deleteCurrentTime(location)
            getCurrentTimeListFromDB()
        }
    }

    fun isCurrentTimeInDB(location: String) {
        viewModelScope.launch {
            isInDB.value = currentTimeDaoUseCase.exists(location)
        }
    }

    fun getCurrentTimeListFromDB() {
        viewModelScope.launch {
            currentTimeFromDB.value = currentTimeDaoUseCase.getAllCurrentTimes().toCurrentTimeList()
        }
    }

    fun getCurrentTime(location: String) {
        viewModelScope.launch {
            currentTimeRepository.getCurrentTime(location).collect {
                when {
                    it.isLoading -> {
                        currentTimeState.value = CurrentTimeState.Loading
                    }
                    it.data == null-> {
                        currentTimeState.value = CurrentTimeState.Error(message = it.message.toString())
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