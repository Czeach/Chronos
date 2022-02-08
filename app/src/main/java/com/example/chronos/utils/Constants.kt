package com.example.chronos.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import androidx.fragment.app.Fragment
import com.example.chronos.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

class Constants() {

    companion object {

        const val base_url = "https://timezone.abstractapi.com/v1/"

    }
}

object TimeUtils {

    @SuppressLint("SimpleDateFormat")
    fun convertToRelativeTime(time: String): String {

        val calendar = Calendar.getInstance()
        val timeFormatter = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")

        val relativeTime = timeFormatter.format(calendar.time)

        return  ""
    }
}