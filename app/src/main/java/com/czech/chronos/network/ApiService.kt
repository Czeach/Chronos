package com.czech.chronos.network

import com.czech.chronos.network.models.ConvertTime
import com.czech.chronos.network.models.CurrentTime
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current_time")
    suspend fun getCurrentTime(
        @Query("api_key") api_key:String,
        @Query("location") location: String
    ): Response<CurrentTime>

    @GET("convert_time")
    suspend fun convertTime(
        @Query("api_key") api_key: String,
        @Query("base_location") base_location: String,
        @Query("base_datetime") base_datetime: String,
        @Query("target_location") target_location: String
    ): Response<ConvertTime>

}