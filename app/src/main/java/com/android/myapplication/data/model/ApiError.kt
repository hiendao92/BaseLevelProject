package com.android.myapplication.data.model

/**
 * @author at-hien.dao
 */
abstract class ApiError : Throwable()

data class NetworkError(
    val code: String?,
    override val message: String?,
    val apiUrl: String?
) : ApiError()

data class DefaultError(
    override val message: String? = null
) : ApiError()
