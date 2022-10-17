package com.example.usecases

import com.example.api.impl.EntriesListImpl
import com.example.api.models.EntityResponse
import kotlinx.coroutines.CoroutineDispatcher

class GetEntriesUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: EntriesListImpl
) {

    suspend fun getEntries(): EntityResponse {
        return repository.entries()
    }

}