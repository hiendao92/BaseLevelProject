package com.android.myapplication.module.network

import com.android.myapplication.data.model.City
import com.android.myapplication.extensions.apiCall
import javax.inject.Inject

/**
 * @author at-hien.dao
 */
class RemoteDataSource @Inject constructor(
    private val api: Api
) : ERemoteDataSource {
    override suspend fun getDataCity(city: String): MutableList<City> = apiCall {
        api.getCities(city)
    }
}
