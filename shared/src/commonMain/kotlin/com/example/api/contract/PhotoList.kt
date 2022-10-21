package com.example.api.contract

import com.example.api.models.PhotosResponseItem

interface PhotoList {

    suspend fun photos(): List<PhotosResponseItem>
}