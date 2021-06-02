package com.abel.cache

import com.abel.common.model.ArticleCategory
import com.abel.data.CategoryCacheDataSource

class CategoryCacheDataSourceImpl : CategoryCacheDataSource {
    override suspend fun getCategories(): List<ArticleCategory> {
        val categorized = followedCategories.filter { availableCategories.contains(it.label) }
        val keywords = followedCategories.filter { !categorized.contains(it) }
            .map { it.copy(isOnApi = false) }
        return categorized + keywords
    }
}
