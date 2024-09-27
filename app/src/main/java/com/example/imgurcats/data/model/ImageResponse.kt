package com.example.imgurcats.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val id: String,
    val title: String?,
    val link: String?,
    @SerialName("is_album")
    val isAlbum: Boolean = false,
    val images: List<ImageItemResponse>? = null,
    val type: String? = null
)
