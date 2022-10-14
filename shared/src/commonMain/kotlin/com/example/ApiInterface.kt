package com.example

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class ApiInterface {

    companion object {
        private const val baseUrl = "https://samples.openweathermap.org"

        private const val url = "$baseUrl/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22"
    }

    private val client = HttpClient()

    suspend fun fetchWeather(): String {
        return client.get(url) {}.bodyAsText()
    }

}