package com.czech.chronos.ui.search

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.chronos.cache.dao.CurrentTimeDaoRepository
import com.czech.chronos.cache.model.CurrentTimeEntity
import com.czech.chronos.interactors.convert.CurrentTimeRepository
import com.czech.chronos.interactors.place.PredictPlaceRepository
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.utils.DateUtil.timeFormat
import com.czech.chronos.utils.DateUtil.timeFromTimeZone
import com.czech.chronos.utils.states.CurrentTimeState
import com.czech.chronos.utils.states.PredictionsState
import com.czech.chronos.utils.toCurrentTimeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val timer: Timer,
    private val currentTimeRepository: CurrentTimeRepository,
    private val predictPlaceRepository: PredictPlaceRepository,
    private val currentTimeDaoRepository: CurrentTimeDaoRepository
): ViewModel() {

    var inputState = mutableStateOf(TextFieldValue(""))
    val predictionsState = MutableStateFlow<PredictionsState?>(null)
    val currentTimeState = MutableStateFlow<CurrentTimeState?>(null)

    val timeState = MutableStateFlow("")
    val isInDB = MutableStateFlow(false)

    fun updateTimeFromServer(timezone: String) {
        val job = viewModelScope.launch {
            val task = object : TimerTask() {
                override fun run() {
                    timeState.value = timeFromTimeZone(timezone)
                }
            }
            timer.schedule(task, 0, 1000)
        }
        job.cancel()
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