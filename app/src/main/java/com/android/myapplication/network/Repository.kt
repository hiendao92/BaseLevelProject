package com.android.myapplication.network

import com.android.myapplication.data.model.City
import com.android.myapplication.extensions.FlowResult
import com.android.myapplication.extensions.safeFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author at-hien.dao
 */
class Repository @Inject constructor(private val dataSource: RemoteDataSource) {
    fun getDataCity(city: String): Flow<FlowResult<MutableList<City>>> = safeFlow {
        dataSource.getDataCity(city)
    }
}
