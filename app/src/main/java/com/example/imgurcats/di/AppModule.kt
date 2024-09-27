package com.example.imgurcats.di

import com.example.imgurcats.data.repository.GetImageRepositoryImpl
import com.example.imgurcats.domain.repository.GetImageRepository
import com.example.imgurcats.presentation.viewmodel.GetImageViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single<GetImageRepository> {
        GetImageRepositoryImpl(get())
    }

    viewModel {
        GetImageViewModel(get())
    }
}
