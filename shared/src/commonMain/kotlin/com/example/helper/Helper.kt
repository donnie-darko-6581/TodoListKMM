package com.example.helper

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// todo withContext() is producing unexpected results for ios, have to dig deeper, hardcoded success not and made no thread switch
suspend fun <T> resultHandler(
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    block: suspend () -> (T)
) = Result.Success(block())/*withContext(dispatcher) {
    return@withContext try {
        val result = block()
        Result.Success(result)
    } catch (ex: Exception) {
        Result.Failure(ex)
    }
}*/

sealed class Result<out T> {
    class Success<T>(val data: T) : Result<T>()
    class Failure(val error: Exception) : Result<Nothing>()
}