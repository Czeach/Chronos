package com.czech.chronos.data.repositories.places

import com.czech.chronos.BuildConfig
import com.czech.chronos.network.PlacesApiService
import com.czech.chronos.network.models.PlacePredictions
import com.czech.chronos.utils.Constants
import com.czech.chronos.utils.DataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.ConnectException
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
	private val placesApiService: PlacesApiService,
	private val dispatcher: CoroutineDispatcher
): PlacesRepository {

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
						if (predictions == null) emit(DataState.success(message = "Can't find city"))

						emit(DataState.success(data = predictions))
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
		}.flowOn(dispatcher)
	}
}