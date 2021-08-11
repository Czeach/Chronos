package com.example.chronos.network

import com.example.chronos.models.ConvertTime
import com.example.chronos.models.CurrentTime
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current_time")
    suspend fun getCurrentTime(
        @Query("api_key") apiKey: String,
        @Query("location") location: String
    ): CurrentTime

    @GET("convert_time")
    suspend fun getConvertTime(
        @Query("api_key") apiKey: String,
        @Query("base_location") baseLocation: String,
        @Query("base_datetime") baseDateTime: String,
        @Query("target_location") targetLocation: String
    ): ConvertTime
}