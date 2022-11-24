package com.example.viewmodels

import com.example.api.models.EntityResponse
import com.example.api.models.PhotosResponseItem
import com.example.base.ViewModel
import com.example.helper.Result
import com.example.usecases.GetEntriesUseCase
import com.example.usecases.PhotoListUseCase
import com.example.viewstate.EntriesViewState
import com.example.viewstate.PhotosViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EntriesViewModel(
    private val entriesUseCase: GetEntriesUseCase,
    private val photosUseCase: PhotoListUseCase
) : ViewModel() {

    private val _entries = MutableStateFlow(EntriesViewState.loading())
    val entries = _entries.asStateFlow()

    private val _photos = MutableStateFlow(PhotosViewState.loading())
    val photos = _photos.asStateFlow()

    init {
        getPhotoList()
        getEntryList()
    }

    /**
     * Fetches a list of photos
     */
    fun getPhotoList() {
        viewModelScope.launch {
            val photos: Result<List<PhotosResponseItem>> = photosUseCase.getPhotos()
            _photos.emit(PhotosViewState.from(photos))
        }
    }

    /**
     * Fetches list of entries
     */
    fun getEntryList() {
        viewModelScope.launch {
            val entries: Result<EntityResponse> = entriesUseCase.getEntries()
            _entries.emit(EntriesViewState.success(entries))
        }
    }

    /**
     * Retries fetching list of entries
     */
    fun retryApi() {
        viewModelScope.launch {
            _entries.emit(EntriesViewState.loading())
            getEntryList()
        }
    }
}

