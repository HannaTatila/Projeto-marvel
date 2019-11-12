package com.example.marvel.data

import com.google.gson.annotations.SerializedName

data class ComicsApiResponse (
    @SerializedName("data") val comicsData: ComicsDataResponse
)