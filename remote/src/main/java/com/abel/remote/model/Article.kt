package com.abel.remote.model

data class Article(
    val source: Source,
    val title: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)
