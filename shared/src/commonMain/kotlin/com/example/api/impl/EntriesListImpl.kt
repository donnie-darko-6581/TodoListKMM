package com.example.api.impl

import com.example.api.contract.EntriesList
import io.ktor.client.*

class EntriesListImpl(client: HttpClient): EntriesList {
    override suspend fun entries(): String {
        TODO("Not yet implemented")
    }
}