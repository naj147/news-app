package com.abel.domain

import com.abel.common.model.NewsCategory
import com.abel.domain.repository.CategoryRepository

interface GetCategoryUseCase {
    suspend fun execute(
    ): List<NewsCategory>
}

internal class GetCategoryUseCaseImpl(private val categoryRepository: CategoryRepository) :
    GetCategoryUseCase {
    override suspend fun execute(
    ): List<NewsCategory> = categoryRepository.getCategories() ?: emptyList()
}
