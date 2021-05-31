package com.abel.presentation.stateObjects

import com.abel.common.model.PageOfNews
import com.abel.presentation.BaseViewState

sealed class ArticleListState : BaseViewState {
    object Uninitialized : ArticleListState()
    data class ArticlesLoaded(
        val pageOfNews: PageOfNews?,
        val resultsSoFar: Int
    ) : ArticleListState()

    data class LoadingNextPage(
        val pageOfNews: PageOfNews?
    ) : ArticleListState()

    data class LoadingFailed(
        val category: String,
        val resultsSoFar: Int,
        val totalResults: Int,
        val totalPages: Int,
        val reason: Throwable? = null
    ) : ArticleListState()
}
