package com.example.kmmlist.archviewmodels.mocks

import com.example.api.contract.PhotoList
import com.example.api.models.PhotosResponseItem

class PhotoListMock: PhotoList {
    override suspend fun photos(): List<PhotosResponseItem> {
        return emptyList()
    }
}