package com.example.viewmodels

import com.example.base.CallbackViewModel
import com.example.usecases.GetEntriesUseCase
import com.example.usecases.PhotoListUseCase

class BreedCallbackViewModel(
    entriesUseCase: GetEntriesUseCase,
    photosUseCase: PhotoListUseCase
) : CallbackViewModel() {

    protected override val viewModel = EntriesViewModel(
        entriesUseCase = entriesUseCase,
        photosUseCase = photosUseCase
    )

    public val iosPhotos = viewModel.photos.asCallbacks()

    fun getPhotoList() {
        viewModel.getPhotoList()
    }
    fun getEntryList() {
        viewModel.getEntryList()
    }
    fun retryApi() {
        viewModel.retryApi()
    }
}