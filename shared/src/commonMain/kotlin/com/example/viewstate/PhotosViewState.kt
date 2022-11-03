package com.example.viewstate

import com.example.api.BaseApiViewState
import com.example.api.models.PhotosResponseItem
import com.example.helper.Result

data class PhotosViewState constructor(
    override val response: Result<List<PhotosResponseItem>>?,
    override val isLoading: Boolean,
    override val error: Exception?,
    val list: List<PhotosResponseItem>? = null // todo: ios kotlin sealed class casting wont work so temp soln
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
            error = null,
            list = (response as Result.Success<List<PhotosResponseItem>>).data
        )
    }

    fun isLoadingContent() = isLoading

    fun isSuccessContent() = response is Result.Success && error == null

    fun isFailureContent() = response is Result.Failure && error != null
}