package com.example.di

import com.example.api.contract.EntriesList
import com.example.api.contract.PhotoList
import com.example.api.impl.EntriesListImpl
import com.example.api.impl.PhotoListImpl
import com.example.usecases.GetEntriesUseCase
import com.example.usecases.PhotoListUseCase
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(appModule: Module): KoinApplication {
    val app = startKoin {
        modules(
            appModule,
            coreModule,
            platformModule
        )
    }

    val koin = app.koin

    // doOnStartup is a lambda which is implemented in Swift on iOS side
    val doOnStartupExample = koin.get<() -> Unit>()
    doOnStartupExample.invoke()

    return app
}

// These are common dependencies across apps
val coreModule = module {
    single<EntriesList> {
        EntriesListImpl(get())
    }
    single<PhotoList> {
        PhotoListImpl(get())
    }

    single<GetEntriesUseCase> {
        GetEntriesUseCase(Dispatchers.Default, get())
    }
    single<PhotoListUseCase> {
        PhotoListUseCase(Dispatchers.Default, get())
    }
    single<HttpClient> {
        HttpClient(get()) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            // Timeout
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }
        }
    }
}

// These are dependencies which are platform specific and they can impl them
expect val platformModule: Module