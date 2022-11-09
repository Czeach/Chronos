package com.czech.chronos.network

import com.czech.chronos.network.models.ConvertTime
import com.czech.chronos.network.models.CurrentTime
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("current_time")
    suspend fun getCurrentTime(
        api_key:String,
        location: String
    ): Response<CurrentTime>

    @GET("convert_time")
    suspend fun convertTime(
        api_key: String,
        base_location: String,
        base_datetime: String,
        target_location: String
    ): Response<ConvertTime>

}