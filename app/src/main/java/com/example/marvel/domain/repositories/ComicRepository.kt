package com.example.marvel.domain.repositories

import com.example.marvel.domain.Comics
import io.reactivex.Single

interface ComicRepository {

    fun getList(): Single<List<Comics>>

}