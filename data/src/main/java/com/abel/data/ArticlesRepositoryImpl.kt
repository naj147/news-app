package com.abel.data

import com.abel.common.model.PageOfArticles
import com.abel.common.model.SearchParam
import com.abel.domain.repository.ArticlesRepository

class ArticlesRepositoryImpl(private val articlesRemoteDataSource: ArticlesRemoteDataSource) : ArticlesRepository {
    override suspend fun getArticles(
        searchParam: SearchParam
    ): PageOfArticles = articlesRemoteDataSource.getArticlesWithCategory(searchParam)
}
