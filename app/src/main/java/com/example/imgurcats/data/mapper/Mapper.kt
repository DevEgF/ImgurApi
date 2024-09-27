package com.example.imgurcats.data.mapper

import com.example.imgurcats.data.model.ImageResponse
import com.example.imgurcats.domain.model.Image

private const val IMAGE_TYPE = "image/jpeg"

fun ImageResponse.toImage(): Image? {
    val imageLink = if (isAlbum && !images.isNullOrEmpty()) {
        val firstImage = images.first()
        if (firstImage.type == IMAGE_TYPE) firstImage.link else null
    } else {
        if (type == IMAGE_TYPE) link else null
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
