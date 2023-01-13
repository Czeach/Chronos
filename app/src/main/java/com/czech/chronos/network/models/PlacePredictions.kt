package com.czech.chronos.network.models


import com.google.gson.annotations.SerializedName

data class PlacePredictions(
    @SerializedName("predictions")
    val predictions: List<Prediction?>?,
    @SerializedName("status")
    val status: String?
) {
    data class Prediction(
        @SerializedName("description")
        val description: String?,
        @SerializedName("place_id")
        val placeId: String?,
        @SerializedName("reference")
        val reference: String?,
    )
}