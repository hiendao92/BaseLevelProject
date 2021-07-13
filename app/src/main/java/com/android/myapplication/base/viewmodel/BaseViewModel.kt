package com.android.myapplication.base.viewmodel

import androidx.lifecycle.ViewModel
import com.android.myapplication.data.model.BaseError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author at-hien.dao
 */
abstract class BaseViewModel : ViewModel(), EViewModelInterface {
    private var loadingState = MutableStateFlow(false)
    private var errorState = MutableStateFlow<BaseError?>(null)

    override fun loadingState(): StateFlow<Boolean> = loadingState

    override fun errorState(): StateFlow<BaseError?> = errorState

    override fun handleError(error: BaseError?) {
        errorState.value = error
    }

    override fun handleLoading(isLoading: Boolean) {
        loadingState.value = isLoading
    }
}
