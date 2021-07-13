package com.android.myapplication.main

import com.android.myapplication.base.viewmodel.BaseViewModel
import com.android.myapplication.data.model.City
import com.android.myapplication.extensions.FlowResult
import com.android.myapplication.extensions.bindLoading
import com.android.myapplication.module.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
    ).bindLoading(this)

}
