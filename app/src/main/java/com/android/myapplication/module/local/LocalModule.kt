package com.android.myapplication.module.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author at-hien.dao
 */
@InstallIn(SingletonComponent::class)
@Module
class LocalModule {
    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ): CityDataBase = Room.databaseBuilder(
        app,
        CityDataBase::class.java,
        "testdb"
    ).fallbackToDestructiveMigration()
        .build()


    @Provides
    fun provideYourDao(db: CityDataBase): CityDao = db.cityDao()
}
