package com.abel.common.model

data class PageOfNews(
    val page: Int,
    val totalResults: Int,
    val articles: List<News>?
)
