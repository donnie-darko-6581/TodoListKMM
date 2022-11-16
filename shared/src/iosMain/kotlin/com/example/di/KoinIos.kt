package com.example.di

import com.example.viewmodels.BreedCallbackViewModel
import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.*
import org.koin.dsl.module

actual val platformModule = module {
    single {
        iosHttpClient()
    }
    single {
        BreedCallbackViewModel(
            get(),
            get()
        )
    }
}

private fun iosHttpClient() = HttpClient(Darwin) {

    install(HttpTimeout) {
        requestTimeoutMillis = 15000L
        connectTimeoutMillis = 15000L
        socketTimeoutMillis = 15000L
    }

    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}