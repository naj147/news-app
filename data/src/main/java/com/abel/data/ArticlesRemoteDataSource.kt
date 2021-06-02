package com.abel.data

import com.abel.common.model.PageOfArticles
import com.abel.common.model.SearchParam

interface ArticlesRemoteDataSource {
    suspend fun getArticlesWithCategory(
        searchParam: SearchParam
    ): PageOfArticles
//
//    suspend fun getLatestNews(
//        searchParam: SearchParam
//    ): PageOfNews
}
