package com.example.marvel.data

import com.google.gson.annotations.SerializedName

class ComicResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("thumbnail") val thumbnailResponse: ThumbnailResponse,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String
)