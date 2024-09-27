package com.example.imgurcats.domain.repository

import com.example.imgurcats.domain.model.Image

interface GetImageRepository {
    suspend fun getImages(page: Int): List<Image>
}