package com.czech.chronos.ui.viewModels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czech.chronos.utils.DateUtil.shortDateFormat
import com.czech.chronos.utils.DateUtil.timeFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(DelicateCoroutinesApi::class)
class HomeViewModel: ViewModel() {

    val getTime = MutableStateFlow(timeFormat.format(Calendar.getInstance().time))
    val getDate = MutableStateFlow(shortDateFormat.format(Calendar.getInstance().time))

    init {
        updateTime()
        updateDate()
    }

    private fun updateTime() {
        CoroutineScope(newSingleThreadContext("device_time_thread")).launch {
            delay(1000)
            while (true) {
                getTime.value = timeFormat.format(Calendar.getInstance().time)
                delay(1000)
            }
        }
    }

    private fun updateDate() {
        CoroutineScope(newSingleThreadContext("device_date_thread")).launch {
            delay(1000)
            while (true) {
                getDate.value = shortDateFormat.format(Calendar.getInstance().time)
                delay(1000)
            }
        }
    }

}