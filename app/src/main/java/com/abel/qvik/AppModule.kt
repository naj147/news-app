package com.abel.qvik

import com.abel.common.BOOLEAN_DEBUG
import com.abel.common.OKHTTP_CLIENT_CACHE
import com.abel.common.model.OkHttpCache
import com.abel.qvik.core.ImageRenderGlideImpl
import com.abel.qvik.core.ImageRenderer
import com.abel.qvik.core.OkHttpCacheImpl
import okhttp3.Cache
import org.koin.android.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named(BOOLEAN_DEBUG)) {
        BuildConfig.DEBUG
    }
    single<ImageRenderer> {
        ImageRenderGlideImpl()
    }
    single<OkHttpCache<Cache>>(named(OKHTTP_CLIENT_CACHE)) {
        OkHttpCacheImpl(get())
    }
}
