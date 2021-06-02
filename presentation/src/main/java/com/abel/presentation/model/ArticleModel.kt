package com.abel.presentation.model

import android.os.Parcelable
import com.abel.common.model.Article
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleModel(
    val title: String,
    val publisher: String,
    val imageUrl: String,
    val date: String,
    val content: String,
    val category: String
) : Parcelable

fun Article.toArticleModel() = ArticleModel(
    title, source, imageUrl, publishedDate, content, category
)
