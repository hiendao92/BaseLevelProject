package com.android.myapplication.module

import com.android.myapplication.data.model.City
import com.android.myapplication.extensions.FlowResult
import com.android.myapplication.extensions.doOnSuccess
import com.android.myapplication.extensions.safeFlow
import com.android.myapplication.extensions.transformToLocal
import com.android.myapplication.module.local.CityDao
import com.android.myapplication.module.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author at-hien.dao
 */
@Singleton
class Repository @Inject constructor(
    private val dataSource: RemoteDataSource,
    private val dataLocalSource: CityDao
) {

    fun getDataCity(city: String): Flow<FlowResult<MutableList<City>>> = safeFlow {
        dataSource.getDataCity(city)
    }.doOnSuccess {
        dataLocalSource.save(it)
    }.transformToLocal {
        dataLocalSource.getCities()
    }
}
