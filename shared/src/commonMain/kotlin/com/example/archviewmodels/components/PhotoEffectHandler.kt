package com.example.archviewmodels.components

import com.example.api.models.PhotosResponseItem
import com.example.archviewmodels.holders.*
import com.example.helper.Result
import com.example.usecases.PhotoListUseCase
import io.github.fededri.arch.interfaces.Processor

class PhotoEffectHandler(
    private val photosUseCase: PhotoListUseCase
) : Processor<PhotoSideEffect, PhotoAction> {
    override suspend fun dispatchSideEffect(effect: PhotoSideEffect): PhotoAction {
        when (effect) {
            FetchPhotoListEffect -> {
                return when (val photosResult: Result<List<PhotosResponseItem>> = photosUseCase.getPhotos()) {
                    is Result.Success -> {
                        PhotoFetchSuccessfulAction(photosResult.data)
                    }
                    is Result.Failure -> {
                        PhotoFetchFailureAction(photosResult.error)
                    }
                }

            }
        }
    }
}
