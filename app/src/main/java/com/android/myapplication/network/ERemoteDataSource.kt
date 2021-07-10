package com.android.myapplication.network

import com.android.myapplication.data.model.City

interface ERemoteDataSource {
    suspend fun getDataCity(city : String): MutableList<City>
}
