package com.abel.presentation.stateObjects

import com.abel.common.model.ArticleCategory
import com.abel.presentation.BaseViewState

sealed class FavoriteCategoryState : BaseViewState {
    object Uninitialized : FavoriteCategoryState()
    object Loading : FavoriteCategoryState()
    data class CategoriesLoaded(val categories: List<ArticleCategory>) : FavoriteCategoryState()
    data class LoadingFailed(
        val reason: Throwable? = null
    ) : FavoriteCategoryState()
}
