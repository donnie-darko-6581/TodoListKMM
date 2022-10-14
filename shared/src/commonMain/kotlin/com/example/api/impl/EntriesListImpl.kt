package com.example.api.impl

import com.example.api.contract.EntriesList
import com.example.api.models.EntityResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class EntriesListImpl(private val client: HttpClient): EntriesList {

    companion object {
        private const val BASE_URL = "https://api.publicapis.org"
        private const val entriesList = "$BASE_URL/entries"
    }
    override suspend fun entries(): EntityResponse {
        return client.get(entriesList).body()
    }
}