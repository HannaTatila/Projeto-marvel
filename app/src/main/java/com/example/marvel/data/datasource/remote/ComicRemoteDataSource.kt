package com.example.marvel.data.datasource.remote

import com.example.marvel.data.ComicResponse
import io.reactivex.Single

interface ComicRemoteDataSource {

    fun getList(): Single<List<ComicResponse>>

}