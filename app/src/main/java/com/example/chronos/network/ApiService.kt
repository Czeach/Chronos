package com.example.chronos.network

import com.example.chronos.network.models.ConvertTime
import com.example.chronos.network.models.CurrentTime
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

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