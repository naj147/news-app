package com.abel.data

import com.abel.common.model.News
import com.abel.common.model.SearchParam
import com.abel.domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsRemoteDataSource: NewsRemoteDataSource) : NewsRepository {
    override suspend fun getNews(
        searchParam: SearchParam
    ): List<News>? = newsRemoteDataSource.getNewsWithCategory(searchParam).articles
}
