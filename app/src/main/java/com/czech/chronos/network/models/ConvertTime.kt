package com.czech.chronos.network.models


import com.google.gson.annotations.SerializedName

data class ConvertTime(
    @SerializedName("base_location")
    val baseLocation: BaseLocation,
    @SerializedName("target_location")
    val targetLocation: TargetLocation
) {
    data class BaseLocation(
        val datetime: String,
        @SerializedName("gmt_offset")
        val gmtOffset: Double,
        @SerializedName("is_dst")
        val isDst: Boolean,
        val latitude: Double,
        val longitude: Double,
        @SerializedName("requested_location")
        val requestedLocation: String,
        @SerializedName("timezone_abbreviation")
        val timezoneAbbreviation: String,
        @SerializedName("timezone_location")
        val timezoneLocation: String,
        @SerializedName("timezone_name")
        val timezoneName: String
    )

    data class TargetLocation(
        val datetime: String,
        @SerializedName("gmt_offset")
        val gmtOffset: Double,
        @SerializedName("is_dst")
        val isDst: Boolean,
        val latitude: Double,
        val longitude: Double,
        @SerializedName("requested_location")
        val requestedLocation: String,
        @SerializedName("timezone_abbreviation")
        val timezoneAbbreviation: String,
        @SerializedName("timezone_location")
        val timezoneLocation: String,
        @SerializedName("timezone_name")
        val timezoneName: String
    )
}