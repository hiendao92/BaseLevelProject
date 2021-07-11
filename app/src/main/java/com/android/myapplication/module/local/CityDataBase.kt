package com.android.myapplication.module.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.myapplication.data.model.City

/**
 * @author at-hien.dao
 */
@Database(entities = [City::class], version = 3)
abstract class CityDataBase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
