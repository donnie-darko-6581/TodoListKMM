package com.example.archviewmodels.holders

import com.example.api.models.PhotosResponseItem
import com.example.helper.Result

data class PhotoState(
    val response: Result<List<PhotosResponseItem>>?,
    val isLoading: Boolean,
    val error: Exception?,
) {
    companion object {
        fun initialState(): PhotoState = PhotoState(response = null, isLoading = false, error = null)
    }
}
