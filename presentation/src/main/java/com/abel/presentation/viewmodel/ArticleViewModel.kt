package com.abel.presentation.viewmodel

import com.abel.presentation.BaseViewModel
import com.abel.presentation.model.ArticleModel
import com.abel.presentation.stateObjects.ArticleState

class ArticleViewModel : BaseViewModel<ArticleState>() {
    init {
        _viewStates.value = ArticleState.Uninitialized
    }

    fun loadArticle(article: ArticleModel) {
        _viewStates.value = ArticleState.Loading
        _viewStates.value = ArticleState.ArticleLoaded(article)
    }
}
