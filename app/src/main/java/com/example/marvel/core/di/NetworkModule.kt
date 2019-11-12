package com.example.marvel.core.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { OkHttpClient.Builder().build() }

    single {
        val getProperty = getProperty<String>(BASE_URL)
         Retrofit.Builder()
            .baseUrl(getProperty)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).client(get())
            .build()
    }
}

const val BASE_URL = "BASE_URL"