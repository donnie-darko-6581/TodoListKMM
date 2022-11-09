package com.example.di

import com.example.api.impl.EntriesListImpl
import com.example.api.impl.PhotoListImpl
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

    return app
}

// These are common dependencies across apps
val coreModule = module {
    single {
        EntriesListImpl(get())
    }
    single {
        PhotoListImpl(get())
    }
}

// These are dependencies which are platform specific and they can impl them
expect val platformModule: Module