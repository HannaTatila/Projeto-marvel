package com.example.marvel.di

import com.example.marvel.data.api.ComicsService
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
import retrofit2.Retrofit

object ComicsModule {

    val presentationModule = module {
        viewModel { ComicsViewModel(get()) }
    }

    val domainModule = module {
        factory<ComicsUseCase> { ComicsUseCaseImpl(get()) }
    }

    val dataModule = module {
        single { get<Retrofit>().create(ComicsService::class.java) }
        factory<ComicRemoteDataSource> { ComicRemoteDataSourceImpl(get()) }
        factory<ComicRepository> { ComicRepositoryImpl(get()) }
    }

    fun load() = loadKoinModules(listOf(presentationModule, domainModule, dataModule))
}