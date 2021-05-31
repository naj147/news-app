package com.abel.domain.repository

import com.abel.common.model.PageOfNews
import com.abel.common.model.SearchParam

interface NewsRepository {
    suspend fun getNews(
        searchParam: SearchParam
    ): PageOfNews
}
