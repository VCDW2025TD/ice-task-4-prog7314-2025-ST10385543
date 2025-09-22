package com.example.memestreamapp.data.model

import com.squareup.moshi.Json

data class Gif(
    val id: String,
    @Json(name = "images") val images: GifImages
)

data class GifImages(
    @Json(name = "original") val original: GifOriginal
)

data class GifOriginal(
    val url: String
)
