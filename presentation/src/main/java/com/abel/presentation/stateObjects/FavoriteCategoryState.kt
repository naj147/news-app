package com.abel.presentation.stateObjects

import com.abel.common.model.NewsCategory
import com.abel.presentation.BaseViewState

sealed class FavoriteCategoryState : BaseViewState {
    object Uninitialized : FavoriteCategoryState()
    object Loading : FavoriteCategoryState()
    data class CategoriesLoaded(val categories: List<NewsCategory>) : FavoriteCategoryState()
    data class LoadingFailed(
        val reason: Throwable? = null
    ) : FavoriteCategoryState()
}
