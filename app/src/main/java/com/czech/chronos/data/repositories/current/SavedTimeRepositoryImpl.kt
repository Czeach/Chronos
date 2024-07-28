package com.czech.chronos.data.repositories.current

import com.czech.chronos.network.models.CurrentTime
import com.czech.chronos.room.useCases.CurrentTimeDaoUseCase
import com.czech.chronos.utils.DataState
import com.czech.chronos.utils.toCurrentTimeList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SavedTimeRepositoryImpl @Inject constructor(
	private val currentTimeDaoUseCase: CurrentTimeDaoUseCase,
	private val dispatcher: CoroutineDispatcher
) : SavedTimeRepository {

	override fun getSavedTime(): Flow<DataState<List<CurrentTime>>> = flow {

		try {
			val savedTimes = currentTimeDaoUseCase.getAllCurrentTimes().toCurrentTimeList()

			emit(DataState.success(data = savedTimes))
		} catch (e: Exception) {
			emit(
				DataState.error(
					message = e.message ?: "An error occurred"
				)
			)
		}
	}.flowOn(dispatcher)
}