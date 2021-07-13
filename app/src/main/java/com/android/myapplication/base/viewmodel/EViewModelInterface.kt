package com.android.myapplication.base.viewmodel

import com.android.myapplication.data.model.BaseError
import kotlinx.coroutines.flow.StateFlow

interface EViewModelInterface {
    fun handleLoading(isLoading: Boolean)

    fun handleError(error: BaseError?)

    fun loadingState(): StateFlow<Boolean>

    fun errorState(): StateFlow<BaseError?>
}
