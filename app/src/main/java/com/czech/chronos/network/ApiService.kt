package com.czech.chronos.network

import com.czech.chronos.domain.models.ConvertTime
import com.czech.chronos.domain.models.CurrentTime

interface ApiService {

    suspend fun getCurrentTime(
        api_key:String,
        location: String
    ): CurrentTime

    suspend fun convertTime(
        api_key: String,
        base_location: String,
        base_datetime: String,
        target_location: String
    ): ConvertTime

}