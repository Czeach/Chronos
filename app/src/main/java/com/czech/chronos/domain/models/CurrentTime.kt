package com.czech.chronos.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentTime(
    val datetime: String,
    @SerialName("gmt_offset")
    val gmtOffset: Int,
    @SerialName("is_dst")
    val isDst: Boolean,
    val latitude: Double,
    val longitude: Double,
    @SerialName("requested_location")
    val requestedLocation: String,
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerialName("timezone_location")
    val timezoneLocation: String,
    @SerialName("timezone_name")
    val timezoneName: String
)