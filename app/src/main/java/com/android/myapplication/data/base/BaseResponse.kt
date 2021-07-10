package com.android.myapplication.data.base

import kotlinx.serialization.SerialName

/**
 * @author at-hien.dao
 */
data class BaseResponse(
    @SerialName("message") val message: String? = ""
)
