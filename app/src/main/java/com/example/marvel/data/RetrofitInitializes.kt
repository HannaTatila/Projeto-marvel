package com.example.marvel.data

import com.example.marvel.data.api.ComicsService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInitializes {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://gateway.marvel.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun webServiceComics(): ComicsService {
        return retrofit.create(ComicsService::class.java)
    }

}