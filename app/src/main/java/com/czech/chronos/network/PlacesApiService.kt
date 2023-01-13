package com.czech.chronos.network

import com.czech.chronos.network.models.PlacePredictions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PlacesApiService {

    @GET
    suspend fun predictPlace(
        @Url url: String,
        @Query("key") api_key:String,
        @Query("types") types: String,
        @Query("input") input: String
    ): Response<PlacePredictions>
}