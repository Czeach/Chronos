package com.czech.chronos.network

import com.czech.chronos.BuildConfig
import com.czech.chronos.domain.models.ConvertTime
import com.czech.chronos.domain.models.CurrentTime
import com.czech.chronos.utils.Constants
import io.ktor.client.*
import io.ktor.client.request.*

class ApiServiceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String
): ApiService  {

    override suspend fun getCurrentTime(
        api_key: String,
        location: String
    ): CurrentTime {
        return httpClient.get {
            url("${Constants.BASE_URL}/current_time/?api_key=${BuildConfig.API_KEY}&location=$location")
        }
    }

    override suspend fun convertTime(
        api_key: String,
        base_location: String,
        base_datetime: String,
        target_location: String
    ): ConvertTime {
        return httpClient.get {
            url("${Constants.BASE_URL}/convert_time/?api_key=${BuildConfig.API_KEY}&base_location=$base_location&base_datetime=$base_datetime&target_location=$target_location")
        }
    }


}