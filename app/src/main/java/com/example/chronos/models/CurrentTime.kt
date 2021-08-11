package com.example.chronos.models


import com.google.gson.annotations.SerializedName

data class CurrentTime(
    val datetime: String?,
    @SerializedName("gmt_offset")
    val gmtOffset: Int?,
    @SerializedName("is_dst")
    val isDst: Boolean?,
    val latitude: Double?,
    val longitude: Double?,
    @SerializedName("requested_location")
    val requestedLocation: String?,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String?,
    @SerializedName("timezone_location")
    val timezoneLocation: String?,
    @SerializedName("timezone_name")
    val timezoneName: String?
)