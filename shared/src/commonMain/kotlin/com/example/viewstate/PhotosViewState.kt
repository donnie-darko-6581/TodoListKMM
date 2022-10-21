package com.example.viewstate

import com.example.api.BaseApiViewState
import com.example.api.models.PhotosResponseItem
import com.example.helper.Result

data class PhotosViewState(
    override val response: Result<List<PhotosResponseItem>>?,
    override val isLoading: Boolean,
    override val error: Exception?
): BaseApiViewState<List<PhotosResponseItem>> {
    companion object {
        fun loading() = PhotosViewState(
            response = null,
            isLoading = true,
            error = null
        )

        fun success(response: Result<List<PhotosResponseItem>>) = PhotosViewState(
            response = response,
            isLoading = false,
            error = null
        )
    }

    fun isLoadingContent() = isLoading

    fun isSuccessContent() = response is Result.Success && error == null

    fun isFailureContent() = response is Result.Failure && error != null
}