package com.czech.chronos.di

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.czech.chronos.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Calendar
import java.util.Date
import java.util.Timer
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object AppModule {

    @[Singleton Provides]
    fun provideApplication(
        @ApplicationContext app: Context
    ): BaseApplication {
        return app as BaseApplication
    }

//    @[Singleton Provides]
//    fun provideSavedState(): SavedStateHandle {
//        return SavedStateHandle()
//    }
}