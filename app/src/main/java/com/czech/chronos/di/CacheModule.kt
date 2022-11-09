package com.czech.chronos.di

import androidx.room.Room
import com.czech.chronos.cache.CurrentTimeDao
import com.czech.chronos.cache.database.CurrentTimeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDatabase(app: BaseApplication): CurrentTimeDatabase {
        return Room
            .databaseBuilder(
                app,
                CurrentTimeDatabase::class.java,
                CurrentTimeDatabase.DATABASE
            ).build()
    }

    @Singleton
    @Provides
    fun provideDao(db: CurrentTimeDatabase): CurrentTimeDao {
        return db.currentTimeDao()
    }

}