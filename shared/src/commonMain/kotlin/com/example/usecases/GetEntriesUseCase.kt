package com.example.usecases

import com.example.api.impl.EntriesListImpl
import kotlinx.coroutines.CoroutineDispatcher

class GetEntriesUseCase(
    val dispatcher: CoroutineDispatcher,
    val repository: EntriesListImpl
) {

    suspend fun getEntries(): String {
        return repository.entries()
    }

}