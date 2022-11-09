package com.czech.chronos.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel: ViewModel() {

    private val timer = Timer()
    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val dateFormat = SimpleDateFormat("EEE, d MMM", Locale.getDefault())
    val getTime = MutableStateFlow(timeFormat.format(Calendar.getInstance().time))
    val getDate = MutableStateFlow(dateFormat.format(Calendar.getInstance().time))

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
                    getDate.value = dateFormat.format(Calendar.getInstance().time)
                }
            }
            timer.schedule(task, 0, 1000)
        }
        job.cancel()
    }

}