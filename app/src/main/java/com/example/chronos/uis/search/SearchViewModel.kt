package com.example.chronos.uis.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.chronos.BuildConfig
import com.example.chronos.models.CurrentTime
import com.example.chronos.network.Repository
import com.example.chronos.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository): ViewModel() {



    var location: String by mutableStateOf(String())
    var searchedList: List<CurrentTime> by mutableStateOf(listOf())


//    fun getLocationList() {
//        liveData(Dispatchers.IO) {
//
//            if (location.isNotEmpty()) {
//                delay(400)
//                emit(Resource.Loading(data = null))
//                try {
//                    emit(Resource.Success(repository.getCurrentTime(BuildConfig.API_KEY, location)))
//                }catch (e: Exception) {
//                    Resource.Error(data = null, message = e.message ?: "Error getting location")
//                }
//            }
//        }
//    }
}