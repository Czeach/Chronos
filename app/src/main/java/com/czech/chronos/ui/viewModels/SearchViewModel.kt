package com.czech.chronos.ui.viewModels

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.chronos.cache.dao.CurrentTimeDaoRepository
import com.czech.chronos.cache.model.CurrentTimeEntity
import com.czech.chronos.interactors.convert.CurrentTimeRepository
import com.czech.chronos.interactors.place.PredictPlaceRepository
import com.czech.chronos.network.models.CurrentTime
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
    private val currentTimeRepository: CurrentTimeRepository,
    private val predictPlaceRepository: PredictPlaceRepository,
    private val currentTimeDaoRepository: CurrentTimeDaoRepository
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

    fun insertCurrentTimeIntoDB(currentTime: CurrentTimeEntity, checked: Boolean) {
        viewModelScope.launch {
            currentTime.checked = checked
            currentTimeDaoRepository.insertCurrentTime(currentTime)
        }
    }

    fun deleteCurrentTimeFromDB(location: String) {
        viewModelScope.launch {
            currentTimeDaoRepository.deleteCurrentTime(location)
        }
    }

    fun isCurrentTimeInDB(location: String) {
        viewModelScope.launch {
            isInDB.value = currentTimeDaoRepository.exists(location)
        }
    }

    fun getCurrentTimeListFromDB() {
        viewModelScope.launch {
            currentTimeFromDB.value = currentTimeDaoRepository.getAllCurrentTimes().toCurrentTimeList()
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