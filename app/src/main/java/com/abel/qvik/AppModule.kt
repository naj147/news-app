package com.abel.qvik

import com.abel.common.BOOLEAN_DEBUG
import com.abel.qvik.core.ImageRenderGlideImpl
import com.abel.qvik.core.ImageRenderer
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
}
