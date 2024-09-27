package com.example.imgurcats.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ImageItemResponse(
    val id: String,
    val link: String,
    val type: String?
)
