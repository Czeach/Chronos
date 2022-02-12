package com.example.chronos.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_times")
data class CurrentTimeEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "datetime")
    val datetime: String,

    @ColumnInfo(name = "gmt_offset")
    val gmtOffset: Int,

    @ColumnInfo(name = "is_dst")
    val isDst: Boolean,

    @ColumnInfo(name = "latitude")
    val latitude: Double,

    @ColumnInfo(name = "longitude")
    val longitude: Double,

    @ColumnInfo(name = "requested_location")
    val requestedLocation: String,

    @ColumnInfo(name = "timezone_abbreviation")
    val timezoneAbbreviation: String,

    @ColumnInfo(name = "timezone_location")
    val timezoneLocation: String,

    @ColumnInfo(name = "timezone_name")
    val timezoneName: String
)