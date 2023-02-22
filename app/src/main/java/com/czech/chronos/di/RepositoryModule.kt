package com.czech.chronos.di

import com.czech.chronos.network.ApiService
import com.czech.chronos.network.PlacesApiService
import com.czech.chronos.repositories.ChronosRepository
import com.czech.chronos.repositories.ChronosRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RepositoryModule {

    @[Provides Singleton]
    fun provideChronosRepository(
        apiService: ApiService,
        placesApiService: PlacesApiService
    ): ChronosRepository {
        return ChronosRepositoryImpl(
            apiService = apiService,
            placesApiService = placesApiService
        )
    }
}