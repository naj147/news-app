package com.abel.remote

import com.abel.common.model.News
import com.abel.common.model.PageOfNews
import com.abel.remote.model.Article
import com.abel.remote.model.NewsRemoteModel


fun NewsRemoteModel.ToPageOfNews(
    page: Int?,
    category: String = ""
): PageOfNews =
    PageOfNews(page ?: 1, totalResults ?: 0, articles?.map { it.toNews(category) })

fun Article.toNews(category: String) =
    News(category, title, urlToImage, source, publishAt, content)
