package com.abel.remote

import com.abel.common.model.Article
import com.abel.common.model.PageOfArticles
import com.abel.remote.model.ArticleRemoteModel
import com.abel.remote.model.NewsRemoteModel
import java.text.SimpleDateFormat
import java.util.*


fun NewsRemoteModel.ToPageOfNews(
    page: Int?,
    category: String = ""
): PageOfArticles =
    PageOfArticles(page ?: 1, totalResults ?: 0, articles?.map { it.toNews(category) })

fun ArticleRemoteModel.toNews(category: String) =
    Article(
        category,
        title,
        urlToImage ?: "",
        source.name ?: "",
        formatDate(publishedAt),
        content ?: "No Content"
    )

fun formatDate(date: String): String {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.getDefault())
        val newDate = format.parse(date)
        SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(newDate)
    } catch (e: Exception) {
        date
    }
}

