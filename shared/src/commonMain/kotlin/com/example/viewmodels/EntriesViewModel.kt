package com.example.viewmodels

import com.example.api.impl.EntriesListImpl
import com.example.api.models.EntityResponse
import com.example.base.ViewModel
import com.example.helper.Result
import com.example.kmmlist.httpClient
import com.example.usecases.GetEntriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EntriesViewModel : ViewModel() {

    private val entriesUseCase = GetEntriesUseCase(
        dispatcher = Dispatchers.Default,
        repository = EntriesListImpl(httpClient())
    )

    private val _entries = MutableStateFlow(EntriesViewState.loading())
    val entries = _entries.asStateFlow()

    fun getEntryList() {
        viewModelScope.launch(Dispatchers.Default) {
            val entries: Result<EntityResponse> = entriesUseCase.getEntries()
            _entries.emit(EntriesViewState.success(entries))
        }
    }

    fun retryApi() {
        viewModelScope.launch {
            _entries.emit(EntriesViewState.loading())
            getEntryList()
        }
    }
}

data class EntriesViewState(
    val response: Result<EntityResponse>?,
    val isLoading: Boolean,
    val error: Exception? // todo should be some generic error struct across app.
) {
    companion object {
        fun loading() = EntriesViewState(
            response = null,
            isLoading = true,
            error = null
        )

        fun success(response: Result<EntityResponse>) = EntriesViewState(
            response = response,
            isLoading = false,
            error = null
        )
    }

    fun isLoadingContent() = isLoading

    fun isSuccessContent() = response is Result.Success && error == null

    fun isFailureContent() = response is Result.Failure && error == null
}