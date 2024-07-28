package com.czech.chronos.ui.screens.search

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.chronos.data.repositories.current.CurrentTimeRepository
import com.czech.chronos.data.repositories.current.SavedTimeRepository
import com.czech.chronos.data.repositories.places.PlacesRepository
import com.czech.chronos.data.states.CurrentTimeState
import com.czech.chronos.data.states.PredictionsState
import com.czech.chronos.data.states.SavedTimesState
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.room.CurrentTimeEntity
import com.czech.chronos.room.useCases.CurrentTimeDaoUseCase
import com.czech.chronos.utils.toCurrentTimeList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import java.util.*
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@HiltViewModel
class SearchViewModel @Inject constructor(
	private val placesRepository: PlacesRepository,
	private val currentTimeRepository: CurrentTimeRepository,
	private val savedTimeRepository: SavedTimeRepository
): ViewModel() {

    val predictionsState = MutableStateFlow<PredictionsState?>(null)
    private val _currentTimeState = MutableStateFlow<CurrentTimeState?>(null)
    val currentTimeState: StateFlow<CurrentTimeState?> = _currentTimeState

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

//    fun insertCurrentTimeIntoDB(currentTime: CurrentTimeEntity, checked: Boolean) {
//        viewModelScope.launch {
//            currentTime.checked = checked
//            currentTimeDaoUseCase.insertCurrentTime(currentTime)
//        }
//    }

//    fun deleteCurrentTimeFromDB(location: String) {
//        viewModelScope.launch {
//            currentTimeDaoUseCase.deleteCurrentTime(location)
//            getCurrentTimeListFromDB()
//        }
//    }

//    fun isCurrentTimeInDB(location: String) {
//        viewModelScope.launch {
//            isInDB.value = currentTimeDaoUseCase.exists(location)
//        }
//    }

    fun getCurrentTimeListFromDB() {
        viewModelScope.launch {
            savedTimeRepository.getSavedTime().collect {
                when {
                    it.isSuccess -> {
                        _currentTimeState.value = CurrentTimeState.Success(data = it.data?.last())
                    }
                    it.isError -> {
                        _currentTimeState.value = it.message?.let { it1 -> CurrentTimeState.Error(message = it1) }
                    }
                }
            }
        }
    }

    fun getCurrentTime(location: String) {
        viewModelScope.launch {
            currentTimeRepository.getCurrentTime(location).collect {
                when {
                    it.isError-> {
                        _currentTimeState.value = CurrentTimeState.Error(message = it.message.toString())
                    }
                    it.isSuccess -> {
                        _currentTimeState.value = CurrentTimeState.Success(data = it.data)
                    }
                }
            }
        }
    }
}