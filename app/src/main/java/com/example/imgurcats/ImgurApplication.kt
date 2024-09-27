package com.example.imgurcats

import android.app.Application
import com.example.imgurcats.di.appModule
import com.example.imgurcats.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ImgurApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ImgurApplication)
            modules(
                networkModule,
                appModule
            )
        }
    }
}
