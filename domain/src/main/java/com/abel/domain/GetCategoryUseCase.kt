package com.abel.domain

import com.abel.common.model.ArticleCategory
import com.abel.domain.repository.CategoryRepository

interface GetCategoryUseCase {
    suspend fun execute(
    ): List<ArticleCategory>
}

internal class GetCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) :
    GetCategoryUseCase {
    override suspend fun execute(
    ): List<ArticleCategory> = categoryRepository.getCategories() ?: emptyList()
}
