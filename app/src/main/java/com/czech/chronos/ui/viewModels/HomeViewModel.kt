package com.czech.chronos.ui.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.room.useCases.CurrentTimeDaoUseCase
import com.czech.chronos.utils.toCurrentTimeList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
	private val currentTimeDaoUseCase: CurrentTimeDaoUseCase
): ViewModel() {

	val savedLocations = MutableStateFlow(listOf<CurrentTime>())

	init {
		getSavedLocationsFromDB()
	}

	fun getSavedLocationsFromDB() {
		viewModelScope.launch {
			savedLocations.value = currentTimeDaoUseCase.getAllCurrentTimes().toCurrentTimeList()
		}
	}
}