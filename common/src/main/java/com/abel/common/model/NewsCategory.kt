package com.abel.common.model

data class NewsCategory(
    val imageResource: String,
    val followers: String,
    val label: String,
    val isOnApi: Boolean = true
)
