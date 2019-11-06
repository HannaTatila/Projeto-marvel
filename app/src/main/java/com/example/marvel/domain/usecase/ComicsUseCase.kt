package com.example.marvel.domain.usecase

import com.example.marvel.domain.Comics
import io.reactivex.Single

interface ComicsUseCase {

    operator fun invoke(): Single<List<Comics>>

}