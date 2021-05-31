package com.abel.presentation.stateObjects

import com.abel.common.model.NewsCategory
import com.abel.presentation.BaseViewState
import com.abel.presentation.model.ArticleModel

sealed class ArticleState : BaseViewState {
    object Uninitialized : ArticleState()
    object Loading : ArticleState()
    data class ArticleLoaded(val article: ArticleModel) : ArticleState()
    data class LoadingFailed(
        val reason: Throwable? = null
    ) : ArticleState()
}
