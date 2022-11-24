package com.example.di

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

actual val platformModule = module {
    single<HttpClientEngine> {
        OkHttp.create {
            config {
                readTimeout(15, TimeUnit.SECONDS)
                writeTimeout(15, TimeUnit.SECONDS)
                retryOnConnectionFailure(false)
                connectTimeout(30, TimeUnit.SECONDS)
                addInterceptor { chain ->
                    chain.proceed(request = chain.request())
                }

            }
        }
    }
}