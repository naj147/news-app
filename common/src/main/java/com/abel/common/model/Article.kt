package com.abel.common.model

data class Article(
    val category: String,
    val title: String,
    val imageUrl: String,
    val source: String,
    val publishedDate: String,
    val content: String
)
