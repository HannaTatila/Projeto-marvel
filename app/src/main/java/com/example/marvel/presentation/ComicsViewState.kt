package com.example.marvel.presentation

import com.example.marvel.domain.Comics

data class ComicsViewState(
    val isLoading: Boolean = false,
    val comicsList: List<Comics> = listOf()
)