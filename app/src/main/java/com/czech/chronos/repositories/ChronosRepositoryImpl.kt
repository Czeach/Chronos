package com.czech.chronos.repositories

import com.czech.chronos.BuildConfig
import com.czech.chronos.network.ApiService
import com.czech.chronos.network.PlacesApiService
import com.czech.chronos.network.models.ConvertTime
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.network.models.PlacePredictions
import com.czech.chronos.repositories.ChronosRepository
import com.czech.chronos.utils.Constants
import com.czech.chronos.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.ConnectException
import javax.inject.Inject

class ChronosRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val placesApiService: PlacesApiService
): ChronosRepository {

    override fun predictPlace(input: String): Flow<DataState<PlacePredictions>> {
        return flow {
            emit(DataState.loading())

            val response = placesApiService.predictPlace(
                Constants.PLACES_API_BASE_URL,
                BuildConfig.GOOGLE_MAPS_API_KEY,
                Constants.PLACES_TYPE,
                input
            )

            val predictions = response.body()

            try {
                when (response.isSuccessful) {
                    true -> {
                        if (predictions == null) emit(DataState.data(message = "Can't find city"))

                        emit(DataState.data(data = predictions))
                    }
                    false -> {
                        emit(DataState.error(message = "Error ${response.code()}"))
                    }
                }
            } catch (e: Exception) {
                emit(
                    DataState.error(
                        message = e.message ?: "An error occurred"
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    DataState.error(
                        message = e.message ?: "An error occurred"
                    )
                )
            }
        }
    }

    override fun getCurrentTime(location: String): Flow<DataState<CurrentTime>> {
        return flow {
            emit(DataState.loading())

            val response = apiService.getCurrentTime(BuildConfig.API_KEY, location)

            val currentTimeData = response.body()

            try {
                when (response.isSuccessful) {
                    true -> {
                        if (currentTimeData == null)
                            emit(DataState.data(message = "No result for $location"))

                        emit(DataState.data(data = currentTimeData))
                    }
                    false -> {
                        emit(DataState.error(message = "Error ${response.code()}"))
                    }
                }
            } catch (e: Exception) {
                emit(
                    DataState.error(
                        message = e.message ?: "An error occurred"
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun convertTime(
        baseLocation: String,
        baseDatetime: String,
        targetLocation: String
    ): Flow<DataState<ConvertTime>> {
        return flow {
            emit(DataState.loading())

            val response = apiService.convertTime(
                BuildConfig.API_KEY,
                baseLocation,
                baseDatetime,
                targetLocation
            )

            val convertTimeData = response.body()

            try {
                when (response.isSuccessful) {
                    true -> {
                        if (convertTimeData == null) emit(DataState.data(message = "Error converting from $baseLocation time to $targetLocation time"))

                        emit(DataState.data(data = convertTimeData))
                    }
                    false -> {
                        emit(DataState.error(message = "Error ${response.code()}"))
                    }
                }
            } catch (e: Exception) {
                emit(
                    DataState.error(
                        message = e.message ?: "An error occurred"
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
    }
}