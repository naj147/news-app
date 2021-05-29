package com.abel.qvik

import com.abel.common.BOOLEAN_DEBUG
import org.koin.android.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named(BOOLEAN_DEBUG)) {
        BuildConfig.DEBUG
    }
}
