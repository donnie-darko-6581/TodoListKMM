package com.example.viewmodels

import com.example.base.CallbackViewModel

class BreedCallbackViewModel: CallbackViewModel() {

    protected override val viewModel = EntriesViewModel()

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