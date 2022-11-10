package com.czech.chronos.interactors.current

import com.czech.chronos.BuildConfig
import com.czech.chronos.interactors.convert.CurrentTimeRepository
import com.czech.chronos.network.ApiService
import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrentTimeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
): CurrentTimeRepository {

    override fun getCurrentTime(location: String): Flow<DataState<CurrentTime>> {
        return flow {
            emit(DataState.loading())

            val response = apiService.getCurrentTime(BuildConfig.API_KEY, location)

            val currentTimeData = response.body()

            try {
                when (response.isSuccessful) {
                    true -> {
                        if (currentTimeData == null) emit(DataState.data(message = "No result for $location"))

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
}