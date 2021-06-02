package com.abel.domain.repository

import com.abel.common.model.PageOfArticles
import com.abel.common.model.SearchParam

interface ArticlesRepository {
    suspend fun getArticles(
        searchParam: SearchParam
    ): PageOfArticles
}
