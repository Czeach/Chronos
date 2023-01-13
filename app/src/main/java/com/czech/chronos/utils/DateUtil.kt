package com.czech.chronos.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateUtil {

    val timeFormat = SimpleDateFormat("HH:mm")
    val shortDateFormat = SimpleDateFormat("EEE, d MMM")
    val longDateFormat = SimpleDateFormat("EEE, d MMM yyyy")


    fun timeFromTimeZone(timeZone: String): String {
        val calendar = Calendar.getInstance()
        val date = calendar.time

        timeFormat.timeZone = TimeZone.getTimeZone(timeZone)
        return timeFormat.format(date)
    }

}