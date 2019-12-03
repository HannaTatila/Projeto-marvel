package com.example.marvel.data.api

import com.example.marvel.data.ComicResponse
import com.example.marvel.data.ComicsApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsService {

    @GET("/v1/public/comics?ts=1&apikey=c41a380d16444cae4dfa217d408336e1&hash=78abe8add9743df8eff80d42a5672cdf")
    //fun getComicsList(@Query("offset") offset: Int): Single<ComicsApiResponse>
    fun getComicsList(): Single<ComicsApiResponse>


    @GET("/v1/public/comics/{id}?ts=1&apikey=c41a380d16444cae4dfa217d408336e1&hash=78abe8add9743df8eff80d42a5672cdf")
    fun getComic(@Query("id") id: Int): Single<ComicResponse>

}
