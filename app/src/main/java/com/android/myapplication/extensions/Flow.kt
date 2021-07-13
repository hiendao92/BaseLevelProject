package com.android.myapplication.extensions

import com.android.myapplication.base.viewmodel.BaseViewModel
import com.android.myapplication.data.model.BaseError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class FlowResult<out T> {
    data class Success<T>(val value: T) : FlowResult<T>()
    data class Error(val baseError: BaseError) : FlowResult<Nothing>()
}

suspend inline fun <T> safeUseCase(
    crossinline block: suspend () -> T,
): FlowResult<T> = try {
    FlowResult.Success(block())
} catch (e: BaseError) {
    FlowResult.Error(e)
}

inline fun <T> safeFlow(
    crossinline block: suspend () -> T,
): Flow<FlowResult<T>> = flow {
    try {
        val repoResult = block()
        emit(FlowResult.Success(repoResult))
    } catch (e: BaseError) {
        emit(FlowResult.Error(e))
    } catch (e: Exception) {
        e.printStackTrace()
        emit(FlowResult.Error(e.toError()))
    }
}

fun <T> observableFlow(block: suspend FlowCollector<T>.() -> Unit): Flow<FlowResult<T>> =
    flow(block)
        .catch { exception ->
            FlowResult.Error(exception.toError())
        }
        .map {
            FlowResult.Success(it)
        }

fun <T> Flow<FlowResult<T>>.onSuccess(action: suspend (T) -> Unit): Flow<FlowResult<T>> =
    transform { result ->
        if (result is FlowResult.Success<T>) {
            action(result.value)
        }
        return@transform emit(result)
    }

fun <T> Flow<FlowResult<T>>.doOnSuccess(
    scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
    action: (T) -> Unit
): Flow<FlowResult<T>> =
    transform { result ->
        if (result is FlowResult.Success<T>) {
            scope.launch {
                action(result.value)
            }
        }
        return@transform emit(result)
    }

fun <T> Flow<FlowResult<T>>.mapSuccess(): Flow<T> =
    transform { result ->
        if (result is FlowResult.Success<T>) {
            emit(result.value)
        }
    }

fun <T> Flow<FlowResult<T>>.onError(
    action: suspend (BaseError) -> Unit = {}
): Flow<FlowResult<T>> =
    transform { result ->
        if (result is FlowResult.Error) {
            action(result.baseError)
        }
        return@transform emit(result)
    }

fun <T> Flow<FlowResult<T>>.transformToLocal(
    block: suspend () -> T
): Flow<FlowResult<T>> =
    transform {
        try {
            val resultLocal = block()
            return@transform emit(FlowResult.Success(resultLocal))
        } catch (e: BaseError) {
            return@transform emit(FlowResult.Error(e))
        } catch (e: Exception) {
            e.printStackTrace()
            return@transform emit(FlowResult.Error(e.toError()))
        }
    }

fun <H, X> Flow<H>.bindLoading(x: X): Flow<H> where  X : BaseViewModel =
    this.onStart {
        x.handleLoading(true)
    }.onCompletion {
        x.handleLoading(false)
    }

fun <H, X> Flow<FlowResult<H>>.bindError(x: X): Flow<FlowResult<H>> where  X : BaseViewModel =
    this.onError {
        x.handleError(it)
    }
