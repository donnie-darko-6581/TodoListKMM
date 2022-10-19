package com.example.kmmlist

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import java.util.concurrent.TimeUnit

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun httpClient(): HttpClient {
   return HttpClient(OkHttp) {

       install(Logging) {
           level = LogLevel.ALL
       }

       // JSON
       /*install(JsonFeature) {
           serializer = KotlinxSerializer(json)
           //or serializer = KotlinxSerializer()
       }*/
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
}