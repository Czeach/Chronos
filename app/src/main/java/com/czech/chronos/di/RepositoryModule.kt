package com.czech.chronos.di

import com.czech.chronos.network.ApiService
import com.czech.chronos.network.PlacesApiService
import com.czech.chronos.repositories.convert.ConvertTimeRepository
import com.czech.chronos.repositories.convert.ConvertTimeRepositoryImpl
import com.czech.chronos.repositories.current.CurrentTimeRepository
import com.czech.chronos.repositories.current.CurrentTimeRepositoryImpl
import com.czech.chronos.repositories.places.PlacesRepository
import com.czech.chronos.repositories.places.PlacesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RepositoryModule {

    @[Provides Singleton]
    fun providePlacesRepository(
        placesApiService: PlacesApiService
    ): PlacesRepository {
        return PlacesRepositoryImpl(
            placesApiService = placesApiService
        )
    }

    @[Provides Singleton]
    fun provideCurrentTimeRepository(
        apiService: ApiService
    ): CurrentTimeRepository {
        return CurrentTimeRepositoryImpl(
            apiService = apiService
        )
    }

    @[Provides Singleton]
    fun provideConvertTimeRepository(
        apiService: ApiService
    ): ConvertTimeRepository {
        return ConvertTimeRepositoryImpl(
            apiService = apiService
        )
    }
}