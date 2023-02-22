package com.czech.chronos.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_times")
data class CurrentTimeEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val gmtOffset: Int?,
    val isDst: Boolean?,
    val latitude: Double?,
    val longitude: Double?,
    val requestedLocation: String?,
    val timezoneAbbreviation: String?,
    val timezoneLocation: String?,
    val timezoneName: String?,
    var checked: Boolean
)