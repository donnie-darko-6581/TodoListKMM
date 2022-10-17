package com.example.kmmlist

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import java.util.concurrent.TimeUnit

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
   return HttpClient(OkHttp) {
       config(this)

       engine {
           config {
               retryOnConnectionFailure(false)
               connectTimeout(30, TimeUnit.SECONDS)
           }
       }
   }
}