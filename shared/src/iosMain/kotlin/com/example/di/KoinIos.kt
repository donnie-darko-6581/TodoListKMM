package com.example.di

import com.example.viewmodels.BreedCallbackViewModel
import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.*
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

// We can pass start up dependencies like logger, preferences handle etc to core module to use.
@Suppress("unused") // Called from Swift
fun initKoinOnIos(
    startUpFunction: () -> Unit
): KoinApplication {
    val koinApp = initKoin(
        appModule = module {
            single { startUpFunction }
        }
    )
    return koinApp
}

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

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getBreedViewModel() = getKoin().get<BreedCallbackViewModel>()
}