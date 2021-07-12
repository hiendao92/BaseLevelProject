package com.android.myapplication.extensions

import com.android.myapplication.data.base.BaseResponse
import com.android.myapplication.data.model.NetworkError
import com.google.gson.Gson
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
    return try {
        exceptionOnSuccessResponse() ?: NetworkError(
            code = code().toString(),
            message = Gson().fromJson(errorBody()?.string() ?: "", BaseResponse::class.java).message
                ?: "",
            apiUrl = this.raw().request.url.toString()
        )
    } catch (ex: Exception) {
        NetworkError(
            code = code().toString(),
            message = Gson().fromJson(errorBody()?.string() ?: "", BaseResponse::class.java).message
                ?: "",
            apiUrl = this.raw().request.url.toString()
        )
    }
}
