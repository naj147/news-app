package com.abel.data

import com.abel.common.model.ArticleCategory
import com.abel.domain.repository.CategoryRepository

class CategoryRepositoryImpl(private val categoryCacheDataSource: CategoryCacheDataSource) :
    CategoryRepository {
    override suspend fun getCategories(): List<ArticleCategory> =
        categoryCacheDataSource.getCategories()
}
