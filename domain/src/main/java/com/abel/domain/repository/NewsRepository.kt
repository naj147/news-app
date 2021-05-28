package com.abel.domain.repository

import com.abel.common.model.News
import com.abel.common.model.SearchParam

interface NewsRepository {
    suspend fun getNews(
        searchParam: SearchParam
    ): List<News>
}
