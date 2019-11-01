package com.example.marvel.di

import com.example.marvel.presentation.ComicsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object ComicsModule {

    private val comicsModule = module {
        viewModel { ComicsViewModel() }
    }

    fun load() = loadKoinModules(listOf(comicsModule))
}