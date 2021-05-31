package com.abel.data

import com.abel.common.model.News
import com.abel.common.model.PageOfNews
import com.abel.common.model.SearchParam
import com.abel.domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsRemoteDataSource: NewsRemoteDataSource) : NewsRepository {
    override suspend fun getNews(
        searchParam: SearchParam
    ): PageOfNews = newsRemoteDataSource.getNewsWithCategory(searchParam)
}
