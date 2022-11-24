package com.example.di

import com.example.viewmodels.BreedCallbackViewModel
import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
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
        BreedCallbackViewModel(
            get(),
            get()
        )
    }
    single<HttpClientEngine> {
        Darwin.create {
            this.configureRequest {
                this.setTimeoutInterval(timeoutInterval = 15000.0)
            }
        }
    }
}

@Suppress("unused") // Called from Swift
object KotlinDependencies : KoinComponent {
    fun getBreedViewModel() = getKoin().get<BreedCallbackViewModel>()
}