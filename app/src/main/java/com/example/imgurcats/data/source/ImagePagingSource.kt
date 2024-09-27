package com.example.imgurcats.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imgurcats.domain.model.Image
import com.example.imgurcats.domain.repository.GetImageRepository

class ImagePagingSource(
    private val repository: GetImageRepository
) : PagingSource<Int, Image>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        return try {
            val currentPage = params.key ?: 1

            val images = repository.getImages(currentPage)

            LoadResult.Page(
                data = images,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (images.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
