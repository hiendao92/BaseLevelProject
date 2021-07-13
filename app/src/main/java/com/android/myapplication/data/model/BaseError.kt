package com.android.myapplication.data.model

/**
 * @author at-hien.dao
 */
abstract class BaseError : Throwable()

data class NetworkError(
    val code: String?,
    override val message: String?,
    val apiUrl: String?
) : BaseError()

data class DefaultError(
    override val message: String? = null
) : BaseError()
