package com.abel.cache

import com.abel.data.CategoryCacheDataSource

class CategoryCacheDataSourceImpl : CategoryCacheDataSource {
    override suspend fun getCategories(): List<String> =
        followedCategories.filter { availableCategories.contains(it) }

    override suspend fun getKeywords(): List<String> =
        followedCategories.filter { !availableCategories.contains(it) }
}
