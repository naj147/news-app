package com.abel.domain

import com.abel.common.model.PageOfArticles
import com.abel.common.model.SearchParam
import com.abel.domain.repository.ArticlesRepository

interface GetArticlesUseCase {
    suspend fun execute(
        searchParam: SearchParam
    ): PageOfArticles
}

internal class GetArticlesUseCaseImpl(private val articlesRepository: ArticlesRepository) : GetArticlesUseCase {
    override suspend fun execute(
        searchParam: SearchParam
    ): PageOfArticles = articlesRepository.getArticles(searchParam)
}
