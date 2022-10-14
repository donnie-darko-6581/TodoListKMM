package com.example.api.contract

import com.example.api.models.EntityResponse

interface EntriesList {

    suspend fun entries(): EntityResponse
}