package com.czech.chronos.repositories.convert

import com.czech.chronos.BuildConfig
import com.czech.chronos.network.ApiService
import com.czech.chronos.network.models.ConvertTime
import com.czech.chronos.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ConvertTimeRepositoryImpl @Inject constructor(
	private val apiService: ApiService
): ConvertTimeRepository {

	override fun convertTime(
		baseLocation: String,
		baseDatetime: String,
		targetLocation: String
	): Flow<DataState<ConvertTime>> {
		return flow {
			emit(DataState.loading())

			try {
				val response = apiService.convertTime(
					BuildConfig.API_KEY,
					baseLocation,
					baseDatetime,
					targetLocation
				)

				val convertTimeData = response.body()

				if (response.isSuccessful) {
					if (convertTimeData == null) emit(DataState.data(message = "Error converting from $baseLocation time to $targetLocation time"))

					emit(DataState.data(data = convertTimeData))
				} else {
					emit(DataState.error(message = "Error ${response.code()}"))
				}
			} catch (e: HttpException) {
				emit(
					DataState.error(
						message = e.message ?: "An error occurred"
					)
				)
			} catch (e: IOException) {
				emit(
					DataState.error(
						message = e.message ?: "An error occurred"
					)
				)
			}
		}.flowOn(Dispatchers.IO)
	}
}