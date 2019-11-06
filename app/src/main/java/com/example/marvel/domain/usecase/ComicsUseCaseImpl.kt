package com.example.marvel.domain.usecase

import com.example.marvel.domain.Comics
import com.example.marvel.domain.repositories.ComicRepository
import io.reactivex.Single

class ComicsUseCaseImpl(private val comicRepository: ComicRepository) : ComicsUseCase {

    override operator fun invoke(): Single<List<Comics>> {
        return comicRepository.getList()
    }

}