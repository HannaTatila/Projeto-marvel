package com.example.marvel.data

import com.google.gson.annotations.SerializedName

data class ComicsDataResponse(
    @SerializedName("results") val comicsResultsList: List<ComicResponse>
)