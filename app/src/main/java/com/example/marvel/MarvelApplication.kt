package com.example.marvel

import android.app.Application
import com.example.marvel.core.di.BASE_URL
import com.example.marvel.core.di.networkModule
import com.example.marvel.di.ComicsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(networkModule)
            ComicsModule.load()
            properties(mapOf(BASE_URL to BuildConfig.API_BASE))
        }
    }
}