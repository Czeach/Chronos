package com.czech.chronos.utils

import android.annotation.SuppressLint
import java.lang.NullPointerException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    @SuppressLint("SimpleDateFormat")
    private val timeFormat = SimpleDateFormat("HH:mm")

    @SuppressLint("SimpleDateFormat")
    private val shortDateFormat = SimpleDateFormat("EEE, d MMM")

//    private val longDateFormat = SimpleDateFormat("EEE, d MMM YYYY")

    fun stringToTime(string: String): Date {
        return timeFormat.parse(string) ?:throw NullPointerException("Could not convert date string to Date object.")
    }

    fun stringToDate(string: String): Date {
        return shortDateFormat.parse(string) ?:throw NullPointerException("Could not convert date string to Date object.")
    }

}