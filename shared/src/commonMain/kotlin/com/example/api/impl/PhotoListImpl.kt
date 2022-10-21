package com.example.api.impl

import com.example.api.contract.PhotoList
import com.example.api.models.PhotosResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PhotoListImpl(private val client: HttpClient): PhotoList {

    companion object {
        private const val BASE_URL = "https://picsum.photos"
        private const val photosList = "$BASE_URL/v2/list"
    }
    override suspend fun photos(): List<PhotosResponseItem> {
        return client.get(photosList).body()
    }
}