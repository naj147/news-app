package com.abel.data

import com.abel.common.model.NewsCategory

interface CategoryCacheDataSource {
    suspend fun getCategories(): List<NewsCategory>
}
