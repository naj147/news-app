package com.abel.presentation

import com.abel.common.model.*
import com.abel.presentation.model.toArticleModel

internal fun getDummyArticle() = Article(
    "",
    "US Covid live news: country in ‘perilous moment’, says former chief scientific adviser - The Guardian",
    "",
    "NYP",
    "",
    "No Content"
).toArticleModel()

