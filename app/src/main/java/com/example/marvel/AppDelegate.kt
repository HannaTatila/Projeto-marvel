package com.example.marvel

import android.app.Application
import com.example.marvel.di.ComicsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class AppDelegate: Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(applicationContext)
            ComicsModule.load()
        }
    }
}