package com.android.myapplication.extensions

import com.android.myapplication.data.JsonObj
import com.android.myapplication.data.base.BaseResponse
import com.android.myapplication.data.model.NetworkError
import kotlinx.serialization.decodeFromString
import retrofit2.Response

inline fun <T, R> Response<T>.mapSuccess(
    crossinline block: (T) -> R
): R {
    val safeBody = body()
    if (this.isSuccessful && safeBody != null) {
        return block(safeBody)
    } else {
        throw toError()
    }
}

fun <T> Response<T>.exceptionOnSuccessResponse(): NetworkError? {
    if (isSuccessful) {
        this.body()?.let { successResponse ->
            if (successResponse is BaseResponse) {
                return NetworkError(
                    code = successResponse.message,
                    message = successResponse.message,
                    apiUrl = this.raw().request.url.toString()
                )
            }
        }
    }
    return null
}

fun <T> Response<T>.toError(): NetworkError {
    try {
        return exceptionOnSuccessResponse() ?: NetworkError(
            code = code().toString(),
            message = JsonObj.JsonConvert.decodeFromString<BaseResponse>(
                errorBody()?.string() ?: ""
            ).message ?: "",
            apiUrl = this.raw().request.url.toString()
        )
    } catch (ex: Exception) {
        return NetworkError(
            code = code().toString(),
            message = JsonObj.JsonConvert.decodeFromString<BaseResponse>(
                errorBody()?.string() ?: ""
            ).message
                ?: "",
            apiUrl = this.raw().request.url.toString()
        )
    }
}
