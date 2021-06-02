package com.abel.domain.repository

import com.abel.common.model.ArticleCategory

interface CategoryRepository {
    suspend fun getCategories(
    ): List<ArticleCategory>?
}
