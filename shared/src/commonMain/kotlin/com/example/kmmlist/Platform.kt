package com.example.kmmlist

import io.ktor.client.*

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient
