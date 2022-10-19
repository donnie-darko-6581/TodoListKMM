package com.example.viewmodels

import com.example.api.impl.EntriesListImpl
import com.example.api.models.EntityResponse
import com.example.base.ViewModel
import com.example.helper.Result
import com.example.kmmlist.httpClient
import com.example.usecases.GetEntriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntriesViewModel : ViewModel() {

    private val entriesUseCase = GetEntriesUseCase(
        dispatcher = Dispatchers.Default,
        repository = EntriesListImpl(httpClient())
    )

    init {
        viewModelScope.launch {
            val entries: Result<EntityResponse> = entriesUseCase.getEntries()
            // collect it in an observable.
        }
    }

}

data class EntriesViewState(
    val response: EntityResponse?,
    val isLoading: Boolean,
    val error: Exception? // todo should be some generic error struct across app.
) {
    companion object {
        fun loading() = EntriesViewState(
            response = null,
            isLoading = true,
            error = null
        )
    }

    fun isLoading() = isLoading

    fun isSuccess() = response != null && error == null

    fun isFailure() = response == null && error == null
}