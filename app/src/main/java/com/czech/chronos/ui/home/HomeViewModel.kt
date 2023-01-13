package com.czech.chronos.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.chronos.utils.DateUtil.shortDateFormat
import com.czech.chronos.utils.DateUtil.timeFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val timer: Timer
): ViewModel() {

    val getTime = MutableStateFlow("")
    val getDate = MutableStateFlow("")

    init {
        updateTime()
        updateDate()
    }

    private fun updateTime() {
        val job = viewModelScope.launch {
            val task = object : TimerTask() {
                override fun run() {
                    getTime.value = timeFormat.format(Calendar.getInstance().time)
                }
            }
            timer.schedule(task, 0, 1000)
        }
        job.cancel()
    }

    private fun updateDate() {
        val job = viewModelScope.launch {
            val task = object : TimerTask() {
                override fun run() {
                    getDate.value = shortDateFormat.format(Calendar.getInstance().time)
                }
            }
            timer.schedule(task, 0, 1000)
        }
        job.cancel()
    }

}