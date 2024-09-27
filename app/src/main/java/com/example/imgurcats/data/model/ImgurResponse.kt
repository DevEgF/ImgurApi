package com.example.imgurcats.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ImgurResponse(
    val data: List<ImageResponse>
)
