package com.example.chronos.network

import com.example.chronos.utils.models.ConvertTime
import com.example.chronos.utils.models.CurrentTime
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    suspend fun getCurrentTime(): CurrentTime

    suspend fun convertTime(): ConvertTime

    companion object {

        fun build(): HttpClient{
            return HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        encodeDefaults = true
                    })
                }
                install(HttpTimeout) {
                    requestTimeoutMillis = 15000L
                    connectTimeoutMillis = 15000L
                    socketTimeoutMillis = 15000L
                }
                defaultRequest {
                    if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
            }
        }
    }
}