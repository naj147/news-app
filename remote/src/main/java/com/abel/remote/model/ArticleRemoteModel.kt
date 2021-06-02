package com.abel.remote.model

data class ArticleRemoteModel(
    val source: Source,
    val title: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)
