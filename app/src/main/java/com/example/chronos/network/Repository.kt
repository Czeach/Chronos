package com.example.chronos.network

import com.example.chronos.models.ConvertTime
import com.example.chronos.models.CurrentTime
import com.example.chronos.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {

    suspend fun getCurrentTime(apiKey: String, location: String): Resource<CurrentTime> {
        val response = try {
            apiService.getCurrentTime(apiKey, location)
        } catch (e: Exception) {
            return Resource.Error(message = "Error getting current time")
        }
        return Resource.Success(data = response)
    }

    suspend fun getConvertTime(apiKey: String, baseLocation: String, baseDateTime: String, targetLocation: String)
    : Resource<ConvertTime> {
        val response = try {
            apiService.getConvertTime(apiKey, baseLocation, baseDateTime, targetLocation)
        }catch (e: Exception) {
            return Resource.Error(message = "Error converting time")
        }
        return Resource.Success(data = response)
    }

}