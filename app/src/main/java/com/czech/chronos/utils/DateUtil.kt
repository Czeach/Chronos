package com.czech.chronos.utils

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SimpleDateFormat")
object DateUtil {

    val timeFormat = SimpleDateFormat("HH:mm")
    val shortDateFormat = SimpleDateFormat("EEE, d MMM")


    fun timeFromTimeZone(timeZone: String): String {
        val timeZoneTime = ZonedDateTime.now(ZoneId.of(timeZone))

        return timeZoneTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

}