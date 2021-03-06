package com.example.chronos.di

import com.example.chronos.network.ApiService
import com.example.chronos.network.Repository
import com.example.chronos.utils.Constants
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService) {
        Repository(apiService)
    }

    @Singleton
    @Provides
    fun provideApiService(): ApiService {

        val gsonBuilder: GsonBuilder = GsonBuilder().serializeNulls()

        return Retrofit.Builder()
            .baseUrl(Constants.base_url)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()
            .create(ApiService::class.java)
    }
}