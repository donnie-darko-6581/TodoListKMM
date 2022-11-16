package com.example.kmmlist.android.application

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.di.initKoin
import org.koin.dsl.module

class KmmListingApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(
            // inject app specific info to common module. Eg: logger, shared prefs
            module {
                single<Context> { this@KmmListingApplication }
                single {
                    { Log.i("KmmListingApplication - common mod code", "Starting Android app with koin DI")}
                }
            }
        )
    }
}