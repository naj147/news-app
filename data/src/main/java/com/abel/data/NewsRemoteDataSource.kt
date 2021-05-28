package com.abel.data

import com.abel.common.model.PageOfNews
import com.abel.common.model.SearchParam

interface NewsRemoteDataSource {
    suspend fun getNewsWithCategory(
        searchParam: SearchParam
    ): PageOfNews

    suspend fun getLatestNews(
        searchParam: SearchParam
    ): PageOfNews
}
