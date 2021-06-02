package com.abel.qvik

import android.app.Application
import com.abel.cache.cacheModule
import com.abel.data.dataModule
import com.abel.domain.domainModule
import com.abel.presentation.presentationModule
import com.abel.remote.remoteModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApplication : Application() {
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        // Initiate Koin
        startKoin {
            androidLogger()
            androidContext(this@NewsApplication)
            modules(
                appModule,
                presentationModule,
                remoteModule,
                cacheModule,
                dataModule,
                domainModule
            )
        }
    }
}
