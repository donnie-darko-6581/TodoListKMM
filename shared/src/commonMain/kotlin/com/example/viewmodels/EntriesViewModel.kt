package com.example.viewmodels

import com.example.api.models.EntityResponse
import com.example.api.models.PhotosResponseItem
import com.example.base.ViewModel
import com.example.helper.Result
import com.example.usecases.GetEntriesUseCase
import com.example.usecases.PhotoListUseCase
import com.example.viewstate.EntriesViewState
import com.example.viewstate.PhotosViewState
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.github.aakira.napier.log
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
        Napier.base(DebugAntilog())
        log {
            "EntriesViewModel init() called"
        }
        getPhotoList()
        getEntryList()
    }

    /**
     * Fetches a list of photos
     */
    fun getPhotoList() {
        log {
            "getPhotoList called"
        }
        viewModelScope.launch {
            val photos: Result<List<PhotosResponseItem>> = photosUseCase.getPhotos()
            log {
                "response for photos in VM : $photos"
            }
            _photos.emit(PhotosViewState.from(photos))
        }
    }

    /**
     * Fetches list of entries
     */
    fun getEntryList() {
        viewModelScope.launch {
            val entries: Result<EntityResponse> = entriesUseCase.getEntries()
            log {
                "response for entries in VM : $entries"
            }
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

