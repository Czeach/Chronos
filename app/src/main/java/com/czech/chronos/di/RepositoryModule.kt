package com.czech.chronos.di

import com.czech.chronos.network.ApiService
import com.czech.chronos.network.PlacesApiService
import com.czech.chronos.data.repositories.convert.ConvertTimeRepository
import com.czech.chronos.data.repositories.convert.ConvertTimeRepositoryImpl
import com.czech.chronos.data.repositories.current.CurrentTimeRepository
import com.czech.chronos.data.repositories.current.CurrentTimeRepositoryImpl
import com.czech.chronos.data.repositories.current.SavedTimeRepository
import com.czech.chronos.data.repositories.current.SavedTimeRepositoryImpl
import com.czech.chronos.data.repositories.places.PlacesRepository
import com.czech.chronos.data.repositories.places.PlacesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
abstract class RepositoryModule {

    @[Binds Singleton]
    abstract fun providePlacesRepository(
        placesRepositoryImpl: PlacesRepositoryImpl
    ): PlacesRepository

    @[Binds Singleton]
    abstract fun provideCurrentTimeRepository(
        currentTimeRepositoryImpl: CurrentTimeRepositoryImpl
    ): CurrentTimeRepository

    @[Binds Singleton]
    abstract fun provideConvertTimeRepository(
        convertTimeRepositoryImpl: ConvertTimeRepositoryImpl
    ): ConvertTimeRepository

    @[Binds Singleton]
    abstract fun provideSavedTimeRepository(
        savedTimeRepositoryImpl: SavedTimeRepositoryImpl
    ): SavedTimeRepository
}