package com.czech.chronos.di

import com.czech.chronos.network.ApiService
import com.czech.chronos.network.PlacesApiService
import com.czech.chronos.repositories.ChronosRepository
import com.czech.chronos.repositories.ChronosRepositoryImpl
import com.czech.chronos.utils.Constants
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
object RetrofitModule {

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
    fun providePlacesApiService(
        retrofit: Retrofit
    ): PlacesApiService {
        return retrofit.create(PlacesApiService::class.java)
    }

    @[Provides Singleton]
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}