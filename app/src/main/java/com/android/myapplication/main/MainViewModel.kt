package com.android.myapplication.main

import com.android.myapplication.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.android.myapplication.data.model.City
import com.android.myapplication.extensions.FlowResult
import kotlinx.coroutines.flow.Flow
import com.android.myapplication.module.Repository
import javax.inject.Inject

/**
 * @author at-hien.dao
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : BaseViewModel(),
    MainContractViewModel {

    companion object {
        private const val KEY_SEARCH = "city"
    }

    override fun getCities(): Flow<FlowResult<MutableList<City>>> = repository.getDataCity(
        KEY_SEARCH
    )

}