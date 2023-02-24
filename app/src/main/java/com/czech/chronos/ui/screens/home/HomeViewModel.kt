package com.czech.chronos.ui.screens.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.chronos.network.models.ConvertTime
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.network.models.PlacePredictions
import com.czech.chronos.repositories.convert.ConvertTimeRepository
import com.czech.chronos.repositories.places.PlacesRepository
import com.czech.chronos.room.useCases.CurrentTimeDaoUseCase
import com.czech.chronos.utils.states.ConvertTimeState
import com.czech.chronos.utils.states.HomePredictionsState
import com.czech.chronos.utils.states.TargetPredictionsState
import com.czech.chronos.utils.toCurrentTimeList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import java.util.*
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
	private val currentTimeDaoUseCase: CurrentTimeDaoUseCase,
	private val placesRepository: PlacesRepository,
	private val convertTimeRepository: ConvertTimeRepository
): ViewModel() {

	val savedLocations = MutableStateFlow(listOf<CurrentTime>())

	var homePredictionsList = mutableStateOf(listOf<PlacePredictions.Prediction?>())
	var targetPredictionsList = mutableStateOf(listOf<PlacePredictions.Prediction?>())
	val homePredictionsState = MutableStateFlow<HomePredictionsState?>(null)
	val targetPredictionsState = MutableStateFlow<TargetPredictionsState?>(null)
	val convertTimeState = MutableStateFlow<ConvertTimeState?>(null)

	init {
		getSavedLocationsFromDB()
	}

	fun getSavedLocationsFromDB() {
		viewModelScope.launch {
			savedLocations.value = currentTimeDaoUseCase.getAllCurrentTimes().toCurrentTimeList()
		}
	}

	fun getHomePredictions(input: String) {
		viewModelScope.launch {
			placesRepository.predictPlace(input).collect {
				when {
					it.isLoading -> {
						homePredictionsState.value = HomePredictionsState.Loading
					}
					it.data == null -> {
						homePredictionsState.value = HomePredictionsState.Error(message = it.message.toString())
					}
					else -> {
						homePredictionsState.value = HomePredictionsState.Success(data = it.data.predictions)
					}
				}
			}
		}
	}
	fun getTargetPredictions(input: String) {
		viewModelScope.launch {
			placesRepository.predictPlace(input).collect {
				when {
					it.isLoading -> {
						targetPredictionsState.value = TargetPredictionsState.Loading
					}
					it.data == null -> {
						targetPredictionsState.value = TargetPredictionsState.Error(message = it.message.toString())
					}
					else -> {
						targetPredictionsState.value = TargetPredictionsState.Success(data = it.data.predictions)
					}
				}
			}
		}
	}

	fun convertTime(homeLocation: String, dateTime: String, targetLocation: String) {
		viewModelScope.launch {
			convertTimeRepository.convertTime(
				baseLocation = homeLocation,
				baseDatetime = dateTime,
				targetLocation = targetLocation
			).collect {
				when {
					it.isLoading -> {
						convertTimeState.value = ConvertTimeState.Loading
					}
					it.data == null -> {
						convertTimeState.value = ConvertTimeState.Error(message = it.message.toString())
					}
					else -> {
						convertTimeState.value = ConvertTimeState.Success(data = it.data)
					}
				}
			}
		}
	}
}