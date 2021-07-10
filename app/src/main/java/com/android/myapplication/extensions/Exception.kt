package com.android.myapplication.extensions

import com.android.myapplication.data.model.ApiError
import com.android.myapplication.data.model.DefaultError
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toError(): ApiError {
    return when (this) {
        is SocketTimeoutException -> DefaultError()
        is UnknownHostException -> DefaultError()
        is ConnectException -> DefaultError()
        else -> DefaultError()
    }
}
