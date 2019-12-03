package com.example.marvel.domain

data class Comics(
    val id: Int,
    val title: String = "",
    val thumbnail: String = "",
    val description: String? = ""
)