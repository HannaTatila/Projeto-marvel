package com.example.marvel.data.datasource.remote

import com.example.marvel.data.ComicResponse
import com.example.marvel.data.api.ComicsService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ComicRemoteDataSourceImpl(private val service: ComicsService) : ComicRemoteDataSource {

    override fun getList(): Single<List<ComicResponse>> {
        return service.getComicsList()
            .subscribeOn(Schedulers.io())
            .map { it.comicsData.comicsResultsList }
    }

}