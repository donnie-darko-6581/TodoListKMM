package com.example.archviewmodels.holders

import com.example.api.models.PhotosResponseItem

sealed class PhotoAction

data class PhotoFetchSuccessfulAction(
    val response: List<PhotosResponseItem>
) : PhotoAction()

data class PhotoFetchFailureAction(
    val error: Exception
) : PhotoAction()
