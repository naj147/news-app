package com.abel.qvik.controller

import android.view.View
import com.abel.common.model.NewsCategory
import com.abel.qvik.core.ImageRenderer
import com.abel.qvik.view.categoryCard
import com.abel.qvik.view.loadingRow
import com.abel.qvik.view.retryRow
import com.airbnb.epoxy.Typed3EpoxyController

class FavoriteCategoryController(
    private val retryClickListener: () -> Unit,
    private val renderer: ImageRenderer?
) : Typed3EpoxyController<List<NewsCategory>, Boolean, Boolean>() {
    override fun buildModels(
        favoriteCategories: List<NewsCategory>?,
        loading: Boolean?,
        failed: Boolean?
    ) {
        favoriteCategories?.forEach {
            categoryCard {
                id(it.label)
                newsCategory(it)
                imageRenderer(this@FavoriteCategoryController.renderer)
                clickListener(View.OnClickListener { })
                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount / 2 }
            }
        }
        if (loading == true) {
            loadingRow {
                id("loading")
                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
            }
        }

        if (failed == true) {
            retryRow {
                id("retry")
                retryClickListener(View.OnClickListener { this@FavoriteCategoryController.retryClickListener.invoke() })
                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
            }
        }
    }
}
