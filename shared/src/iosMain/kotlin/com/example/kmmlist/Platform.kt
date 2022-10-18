package com.example.kmmlist

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.*
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Darwin) {
        config(this)

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

}