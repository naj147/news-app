package com.abel.data

import com.abel.common.model.ArticleCategory

interface CategoryCacheDataSource {
    suspend fun getCategories(): List<ArticleCategory>
}
