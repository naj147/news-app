package com.abel.data

import com.abel.common.model.NewsCategory
import com.abel.domain.repository.CategoryRepository

class CategoryRepositoryImpl(private val categoryCacheDataSource: CategoryCacheDataSource) :
    CategoryRepository {
    override suspend fun getCategories(): List<NewsCategory> =
        categoryCacheDataSource.getCategories()
}
