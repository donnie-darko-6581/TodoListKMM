package com.example.usecases

import com.example.api.contract.PhotoList
import com.example.api.models.PhotosResponseItem
import com.example.helper.Result
import com.example.helper.resultHandler
import kotlinx.coroutines.CoroutineDispatcher

class PhotoListUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: PhotoList
) {

    suspend fun getPhotos(): Result<List<PhotosResponseItem>> {
        return resultHandler(dispatcher) { repository.photos() }
    }
}