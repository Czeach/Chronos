package com.czech.chronos.ui.screens.home

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.chronos.data.repositories.convert.ConvertTimeRepository
import com.czech.chronos.data.repositories.current.SavedTimeRepository
import com.czech.chronos.data.repositories.current.SavedTimeRepositoryImpl
import com.czech.chronos.data.repositories.places.PlacesRepository
import com.czech.chronos.data.states.SavedTimesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.*
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class HomeViewModel @Inject constructor(
	private val savedTimeRepository: SavedTimeRepository,
	private val placesRepository: PlacesRepository,
	private val convertTimeRepository: ConvertTimeRepository
): ViewModel() {

	private val _savedTimeState = MutableStateFlow<SavedTimesState?>(null)
	val savedTimeState: StateFlow<SavedTimesState?> = _savedTimeState

//	var homePredictionsList = mutableStateOf(listOf<PlacePredictions.Prediction?>())
//	var targetPredictionsList = mutableStateOf(listOf<PlacePredictions.Prediction?>())
//	val homePredictionsState = MutableStateFlow<HomePredictionsState?>(null)
//	val targetPredictionsState = MutableStateFlow<TargetPredictionsState?>(null)
//	val convertTimeState = MutableStateFlow<ConvertTimeState?>(null)
//	var convertTimeResult = mutableStateOf<ConvertTime?>(null)

	init {
		getSavedLocations()
	}

	fun getSavedLocations() {
		viewModelScope.launch {
			savedTimeRepository.getSavedTime().collect {
				when {
					it.isSuccess -> {
						_savedTimeState.value = it.data?.let { list -> SavedTimesState.Success(data = list) }
					}
					it.isError -> {
						_savedTimeState.value = SavedTimesState.Error(message = it.message)
					}
				}
			}
		}
	}

	fun getHomePredictions(input: String) {
		viewModelScope.launch {
			placesRepository.predictPlace(input).collect {
				when {
//					it.isLoading -> {
//						homePredictionsState.value = HomePredictionsState.Loading
//					}
//					it.data == null -> {
//						homePredictionsState.value = HomePredictionsState.Error(message = it.message.toString())
//					}
//					else -> {
//						homePredictionsState.value = HomePredictionsState.Success(data = it.data.predictions)
//					}
				}
			}
		}
	}
	fun getTargetPredictions(input: String) {
		viewModelScope.launch {
			placesRepository.predictPlace(input).collect {
				when {
//					it.isLoading -> {
//						targetPredictionsState.value = TargetPredictionsState.Loading
//					}
//					it.data == null -> {
//						targetPredictionsState.value = TargetPredictionsState.Error(message = it.message.toString())
//					}
//					else -> {
//						targetPredictionsState.value = TargetPredictionsState.Success(data = it.data.predictions)
//					}
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
//					it.isLoading -> {
//						convertTimeState.value = ConvertTimeState.Loading
//					}
//					it.data == null -> {
//						convertTimeState.value = ConvertTimeState.Error(message = it.message.toString())
//					}
//					else -> {
//						convertTimeState.value = ConvertTimeState.Success(data = it.data)
//					}
				}
			}
		}
	}
}