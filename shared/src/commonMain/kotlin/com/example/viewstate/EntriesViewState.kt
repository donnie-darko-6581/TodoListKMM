package com.example.viewstate

import com.example.api.BaseApiViewState
import com.example.api.models.EntityResponse
import com.example.helper.Result

data class EntriesViewState(
    override val response: Result<EntityResponse>?,
    override val isLoading: Boolean,
    override val error: Exception? // todo should be some generic error struct across app.
): BaseApiViewState<EntityResponse> {
    companion object {
        fun loading() = EntriesViewState(
            response = null,
            isLoading = true,
            error = null
        )

        fun success(response: Result<EntityResponse>) = EntriesViewState(
            response = response,
            isLoading = false,
            error = null
        )
    }

    fun isLoadingContent() = isLoading

    fun isSuccessContent() = response is Result.Success && error == null

    fun isFailureContent() = response is Result.Failure && error != null
}