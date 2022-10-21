package com.example.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotosResponseItem(
    @SerialName("author")
    val author: String,
    @SerialName("download_url")
    val download_url: String,
    @SerialName("height")
    val height: Int,
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int
)