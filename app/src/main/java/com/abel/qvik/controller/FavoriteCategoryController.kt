package com.abel.qvik.controller

import android.view.View
import com.abel.common.model.NewsCategory
import com.abel.qvik.core.ImageRenderer
import com.abel.qvik.view.categoryCard
import com.abel.qvik.view.loadingRow
import com.abel.qvik.view.retryRow
import com.airbnb.epoxy.Typed3EpoxyController

class FavoriteCategoryController(
    private val renderer: ImageRenderer?,
    private val retryClickListener: () -> Unit,
    private val navigation: (newsCategory: NewsCategory) -> Unit
) : Typed3EpoxyController<List<NewsCategory>, Boolean, Boolean>() {
    override fun buildModels(
        favoriteCategories: List<NewsCategory>?,
        loading: Boolean?,
        failed: Boolean?
    ) {
        check(!(loading ?: false && failed ?: false)) { "Something is wrong" }

        favoriteCategories?.forEach { newsCategory ->
            categoryCard {
                id(newsCategory.label)
                newsLabel(newsCategory.label)
                newsImageUrl(newsCategory.imageResource)
                imageRenderer(this@FavoriteCategoryController.renderer)
                clickListener(View.OnClickListener {
                    this@FavoriteCategoryController.navigation(newsCategory)
                })
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
