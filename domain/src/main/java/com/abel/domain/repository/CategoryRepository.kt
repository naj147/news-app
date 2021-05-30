package com.abel.domain.repository

import com.abel.common.model.NewsCategory

interface CategoryRepository {
    suspend fun getCategories(
    ): List<NewsCategory>?
}
