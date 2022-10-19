package com.example.viewmodels

import com.example.api.impl.EntriesListImpl
import com.example.api.models.EntityResponse
import com.example.base.ViewModel
import com.example.helper.Result
import com.example.kmmlist.httpClient
import com.example.usecases.GetEntriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntriesViewModel: ViewModel() {

    private val entriesUseCase = GetEntriesUseCase(Dispatchers.Default, EntriesListImpl(httpClient()))


    init {
        viewModelScope.launch {
            val entries: Result<EntityResponse> = entriesUseCase.getEntries()
            // collect it in an observable.
        }
    }

}