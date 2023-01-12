package com.czech.chronos.di

import com.czech.chronos.interactors.convert.ConvertTimeRepositoryImpl
import com.czech.chronos.interactors.convert.CurrentTimeRepository
import com.czech.chronos.interactors.current.ConvertTimeRepository
import com.czech.chronos.interactors.current.CurrentTimeRepositoryImpl
import com.czech.chronos.network.ApiService
import com.czech.chronos.utils.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object NetworkModule {

    @[Provides Singleton]
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @[Provides Singleton]
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @[Provides Singleton]
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @[Provides Singleton]
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
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