package com.example.chronos.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConvertTime(
    @SerialName("base_location")
    val baseLocation: BaseLocation?,
    @SerialName("target_location")
    val targetLocation: TargetLocation?
) {
    @Serializable
    data class BaseLocation(
        val datetime: String?,
        @SerialName("gmt_offset")
        val gmtOffset: Double?,
        @SerialName("is_dst")
        val isDst: Boolean?,
        val latitude: Double?,
        val longitude: Double?,
        @SerialName("requested_location")
        val requestedLocation: String?,
        @SerialName("timezone_abbreviation")
        val timezoneAbbreviation: String?,
        @SerialName("timezone_location")
        val timezoneLocation: String?,
        @SerialName("timezone_name")
        val timezoneName: String?
    )

    @Serializable
    data class TargetLocation(
        val datetime: String?,
        @SerialName("gmt_offset")
        val gmtOffset: Double?,
        @SerialName("is_dst")
        val isDst: Boolean?,
        val latitude: Double?,
        val longitude: Double?,
        @SerialName("requested_location")
        val requestedLocation: String?,
        @SerialName("timezone_abbreviation")
        val timezoneAbbreviation: String?,
        @SerialName("timezone_location")
        val timezoneLocation: String?,
        @SerialName("timezone_name")
        val timezoneName: String?
    )
}