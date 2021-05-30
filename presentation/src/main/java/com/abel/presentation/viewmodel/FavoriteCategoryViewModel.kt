package com.abel.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.abel.domain.GetCategoryUseCase
import com.abel.presentation.BaseViewModel
import com.abel.presentation.stateObjects.FavoriteCategoryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FavoriteCategoryViewModel(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val backgroundContext: CoroutineContext = Dispatchers.Default
) : BaseViewModel<FavoriteCategoryState>() {
    init {
        _viewStates.value = FavoriteCategoryState.Uninitialized
        _viewStates.value = FavoriteCategoryState.Loading
        viewModelScope.launch {
            _viewStates.value = FavoriteCategoryState.CategoriesLoaded(
                try {
                    withContext(backgroundContext) {
                        getCategoryUseCase.execute()
                    }
                } catch (_: Throwable) {
                    emptyList()
                }
            )
        }
    }

    fun loadCategories() {
        _viewStates.value = FavoriteCategoryState.Loading
        viewModelScope.launch {
            _viewStates.value = FavoriteCategoryState.CategoriesLoaded(
                try {
                    withContext(backgroundContext) {
                        getCategoryUseCase.execute()
                    }
                } catch (_: Throwable) {
                    emptyList()
                }
            )
        }
    }
}
