package com.android.myapplication.module.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.myapplication.data.model.City

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(cities: MutableList<City>)

    @Query("SELECT * FROM cities WHERE id = :cityId")
    suspend fun load(cityId: String): City

    @Query("SELECT * FROM cities")
    suspend fun getCities(): MutableList<City>
}
