package com.android.myapplication.main

import com.android.myapplication.data.model.City
import com.android.myapplication.extensions.FlowResult
import kotlinx.coroutines.flow.Flow

interface MainContractViewModel {
    fun getCities(): Flow<FlowResult<MutableList<City>>>
}
