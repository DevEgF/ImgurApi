package com.example.imgurcats.data.repository

import com.example.imgurcats.data.mapper.toImage
import com.example.imgurcats.data.model.ImgurResponse
import com.example.imgurcats.domain.model.Image
import com.example.imgurcats.domain.repository.GetImageRepository
import com.example.imgurcats.utils.Constants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

class GetImageRepositoryImpl(
    private val client: HttpClient
) : GetImageRepository {

    override suspend fun getImages(page: Int): List<Image> {
        val response: HttpResponse = client.get {
            parameter(PARAMS, QUERY_PARAM)
            parameter(PAGE_PARAM, page)
        }

        if (response.status != HttpStatusCode.OK) {
            throw Exception("Failed to fetch images: ${response.status.description}")
        }

        val imgurResponse: ImgurResponse = response.body()

        return imgurResponse.data
            .mapNotNull { it.toImage() }
            .filter { image -> image.link.isNotBlank() }
    }

    private companion object {
        const val QUERY_PARAM = "cats"
        const val PAGE_PARAM = "page"
        const val PARAMS = "q"
    }
}
