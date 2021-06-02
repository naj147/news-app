package com.abel.common.model

data class PageOfArticles(
    val page: Int,
    val totalResults: Int,
    val articles: List<Article>?
)
