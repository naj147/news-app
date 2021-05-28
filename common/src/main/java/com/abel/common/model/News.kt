package com.abel.common.model

data class News(
    val category: String,
    val title: String,
    val imageUrl: String,
    val source: String,
    val publishedDate: String,
    val content: String
)
