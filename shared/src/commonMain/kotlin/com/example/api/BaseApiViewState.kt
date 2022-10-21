package com.example.api

import com.example.helper.Result

interface BaseApiViewState<T> {
    val response: Result<T>?
    val isLoading: Boolean
    val error: Exception?
}