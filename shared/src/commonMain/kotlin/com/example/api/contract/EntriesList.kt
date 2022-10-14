package com.example.api.contract

interface EntriesList {

    suspend fun entries(): String
}