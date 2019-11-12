package com.example.marvel.di

import com.example.marvel.data.RetrofitInitializes
import com.example.marvel.data.datasource.remote.ComicRemoteDataSource
import com.example.marvel.data.datasource.remote.ComicRemoteDataSourceImpl
import com.example.marvel.data.repository.ComicRepositoryImpl
import com.example.marvel.domain.repositories.ComicRepository
import com.example.marvel.domain.usecase.ComicsUseCase
import com.example.marvel.domain.usecase.ComicsUseCaseImpl
import com.example.marvel.presentation.ComicsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object ComicsModule {

    private val presentationModule = module {
        viewModel { ComicsViewModel(get(), get()) }

    }

    private val domainModule = module {
        factory<ComicsUseCase> { ComicsUseCaseImpl(get()) }
    }

    private val dataModule = module {
        single { RetrofitInitializes.webServiceComics() }
        factory<ComicRemoteDataSource> { ComicRemoteDataSourceImpl(get()) }
        factory<ComicRepository> { ComicRepositoryImpl(get()) }
    }

    fun load() = loadKoinModules(listOf(presentationModule, domainModule, dataModule))
}