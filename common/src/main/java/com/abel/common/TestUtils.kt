package com.abel.common

import com.abel.common.model.*

fun getDummyArticleCategories() = listOf(
    ArticleCategory("https://i.imgur.com/aZGiYMx.png", "321k", "fashion"),
    ArticleCategory("https://i.imgur.com/T96WYUX.png", "120k", "science"),
    ArticleCategory("https://i.imgur.com/sAVZmrZ.png", "1.1m", "auto")
)

fun getDummyPageOfArticles(): PageOfArticles = PageOfArticles(
    1, 447, listOf(
        Article(
            "",
            "UK Covid live news: country in ‘perilous moment’, says former chief scientific adviser - The Guardian",
            "",
            "The Guardian",
            "",
            "No Content"
        ), Article(
            "",
            "Daniel Boulton: Manhunt after mother and son found dead in Louth - BBC News",
            "urlToImage ?: ",
            "BBC News",
            "",
            "No Content"
        )
    )
)

fun getDummySearchParam() = SearchParam("one", "", SortType.POPULARITY, 1)

fun getDummyPageOfArticles2(): PageOfArticles = PageOfArticles(
    2, 447, listOf(
        Article(
            "",
            "US Covid live news: country in ‘perilous moment’, says former chief scientific adviser - The Guardian",
            "",
            "NYP",
            "",
            "No Content"
        ), Article(
            "",
            "Daniel Boulton: Manhunt after mother and son found dead in Louth - BBC News",
            "urlToImage ?: ",
            "EL",
            "",
            "No Content"
        )
    )
)
