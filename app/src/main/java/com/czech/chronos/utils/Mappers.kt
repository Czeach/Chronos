package com.czech.chronos.utils

import com.czech.chronos.room.CurrentTimeEntity
import com.czech.chronos.network.models.CurrentTime

fun CurrentTime.toCurrentTimeEntity(): CurrentTimeEntity {
    return CurrentTimeEntity(
        id = null,
        gmtOffset = gmtOffset,
        isDst = isDst,
        latitude = latitude,
        longitude = longitude,
        requestedLocation = requestedLocation,
        timezoneAbbreviation = timezoneAbbreviation,
        timezoneLocation = timezoneLocation,
        timezoneName = timezoneName,
        checked = checked
    )
}

fun CurrentTimeEntity.toCurrentTime(): CurrentTime {
    return CurrentTime(
        gmtOffset = gmtOffset,
        isDst = isDst,
        latitude = latitude,
        longitude = longitude,
        requestedLocation = requestedLocation,
        timezoneAbbreviation = timezoneAbbreviation,
        timezoneLocation = timezoneLocation,
        timezoneName = timezoneName,
        checked = checked
    )
}

fun List<CurrentTimeEntity>.toCurrentTimeList(): List<CurrentTime> {
    return map { it.toCurrentTime() }
}