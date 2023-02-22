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

    val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val shortDateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("EEE, d MMM")
    val longDateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy")

}