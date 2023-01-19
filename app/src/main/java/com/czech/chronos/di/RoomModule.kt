package com.czech.chronos.di

import androidx.room.Room
import com.czech.chronos.BaseApplication
import com.czech.chronos.room.dao.CurrentTimeDao
import com.czech.chronos.room.dao.CurrentTimeDaoUseCase
import com.czech.chronos.room.dao.CurrentTimeDaoUseCaseImpl
import com.czech.chronos.room.CurrentTimeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {

    @[Singleton Provides]
    fun provideDatabase(app: BaseApplication): CurrentTimeDatabase {
        return Room
            .databaseBuilder(
                app,
                CurrentTimeDatabase::class.java,
                CurrentTimeDatabase.DATABASE
            ).build()
    }

    @[Singleton Provides]
    fun provideDao(db: CurrentTimeDatabase): CurrentTimeDao {
        return db.currentTimeDao()
    }

    @[Singleton Provides]
    fun provideCurrentTimeDaoRepository(
        currentTimeDao: CurrentTimeDao
    ): CurrentTimeDaoUseCase {
        return CurrentTimeDaoUseCaseImpl(
            currentTimeDao = currentTimeDao
        )
    }

}