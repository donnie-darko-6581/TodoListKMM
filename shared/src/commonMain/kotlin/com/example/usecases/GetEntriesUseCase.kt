package com.example.usecases

import com.example.api.impl.EntriesListImpl
import com.example.api.models.EntityResponse
import com.example.helper.Result
import com.example.helper.resultHandler
import kotlinx.coroutines.CoroutineDispatcher

class GetEntriesUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: EntriesListImpl
) {

    suspend fun getEntries(): Result<EntityResponse> {
        return resultHandler(dispatcher) { repository.entries() }
    }

}