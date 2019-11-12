package com.example.marvel.data.repository

import com.example.marvel.data.datasource.remote.ComicRemoteDataSource
import com.example.marvel.domain.Comics
import com.example.marvel.domain.repositories.ComicRepository
import io.reactivex.Observable
import io.reactivex.Single

class ComicRepositoryImpl(private val comicRemoteDataSource: ComicRemoteDataSource) :
    ComicRepository {

    override fun getList(): Single<List<Comics>> {
        return comicRemoteDataSource.getList()
            .flatMap { comicResponseList ->
                Observable.fromIterable(comicResponseList)
                    .map { comicResponse ->
                        val thumbnail = getThumbnailFinalPath(
                            comicResponse.thumbnailResponse.path,
                            comicResponse.thumbnailResponse.extension
                        )

                        Comics(
                            comicResponse.title,
                            thumbnail,
                            comicResponse.description
                        )
                    }.toList()
            }
    }

    private fun getThumbnailFinalPath(path: String, extension: String) = "$path.$extension"

}
