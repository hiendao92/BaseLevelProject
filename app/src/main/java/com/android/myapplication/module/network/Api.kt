package com.android.myapplication.module.network

import com.android.myapplication.data.model.City
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("location/search/")
    suspend fun getCities(@Query("query") query: String): Response<MutableList<City>>
}
