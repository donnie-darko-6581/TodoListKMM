package com.example.di

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

actual val platformModule = module {
    single {
        androidHttpClient()
    }
}

private fun androidHttpClient() = HttpClient(OkHttp) {

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("LALIT", message)
            }
        }

        level = LogLevel.ALL
    }

    // Timeout
    install(HttpTimeout) {
        requestTimeoutMillis = 15000L
        connectTimeoutMillis = 15000L
        socketTimeoutMillis = 15000L
    }

    engine {
        config {
            retryOnConnectionFailure(false)
            connectTimeout(30, TimeUnit.SECONDS)
        }
    }
}