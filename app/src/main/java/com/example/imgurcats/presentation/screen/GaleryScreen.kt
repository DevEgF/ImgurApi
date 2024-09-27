package com.example.imgurcats.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.LazyPagingItems
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import androidx.paging.LoadState
import com.example.imgurcats.domain.model.Image
import com.example.imgurcats.presentation.viewmodel.GetImageViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun GalleryScreen(viewModel: GetImageViewModel = getViewModel()) {
    val images = viewModel.images.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        when (images.loadState.refresh) {
            is LoadState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            else -> {
                ImageGrid(images = images)
            }
        }
    }
}

@Composable
fun ImageGrid(images: LazyPagingItems<Image>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.padding(16.dp)
    ) {
        items(images.itemCount) { index ->
            val image = images[index]

            image?.let {
                SubcomposeAsyncImage(
                    model = it.link,
                    contentDescription = it.title,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(100.dp)
                ) {
                    when (painter.state) {
                        is coil.compose.AsyncImagePainter.State.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        else -> {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }
            }
        }

        if (images.loadState.append == LoadState.Loading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        if (images.loadState.append is LoadState.Error) {
            val error = (images.loadState.append as LoadState.Error).error
            item {
                Text(
                    text = "Error: ${error.localizedMessage}",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
