package com.abel.cache

import com.abel.data.CategoryCacheDataSource
import org.koin.dsl.module

val cacheModule = module {
    single<CategoryCacheDataSource> { CategoryCacheDataSourceImpl() }
}
