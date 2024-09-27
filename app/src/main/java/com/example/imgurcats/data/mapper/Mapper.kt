package com.example.imgurcats.data.mapper

import com.example.imgurcats.data.model.ImageResponse
import com.example.imgurcats.domain.model.Image

fun ImageResponse.toImage(): Image? {
    val imageLink = if (isAlbum && !images.isNullOrEmpty()) {
        val firstImage = images.first()
        if (firstImage.type == "image/jpeg") firstImage.link else null
    } else {
        if (type == "image/jpeg") link else null
    }

    return if (!imageLink.isNullOrBlank()) {
        Image(
            id = id,
            title = title,
            link = imageLink
        )
    } else {
        null
    }
}
