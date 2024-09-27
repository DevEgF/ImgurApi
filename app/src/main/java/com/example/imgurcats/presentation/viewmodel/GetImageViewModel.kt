package com.example.imgurcats.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.imgurcats.data.source.ImagePagingSource
import com.example.imgurcats.domain.model.Image
import com.example.imgurcats.domain.repository.GetImageRepository
import kotlinx.coroutines.flow.Flow

class GetImageViewModel(
    private val repository: GetImageRepository
) : ViewModel() {

    val images: Flow<PagingData<Image>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { ImagePagingSource(repository) }
    ).flow.cachedIn(viewModelScope)

    private companion object {
        const val PAGE_SIZE = 20
    }
}
