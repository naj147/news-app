package com.abel.qvik.controller

import android.view.View
import com.abel.presentation.model.ArticleModel
import com.abel.presentation.model.HeaderModel
import com.abel.qvik.core.PageLoadingHandler
import com.abel.qvik.view.articleRow
import com.abel.qvik.view.headerCard
import com.abel.qvik.view.loadingRow
import com.abel.qvik.view.retryRow
import com.airbnb.epoxy.Typed4EpoxyController

class ArticlesController(
    private val pageLoadingHandler: PageLoadingHandler,
    private val onArticleClick: (articleModel: ArticleModel) -> Unit,
    private val retryClickListener: () -> Unit
) :
    Typed4EpoxyController<List<ArticleModel>, HeaderModel, Boolean, Boolean>() {
    override fun buildModels(
        articles: List<ArticleModel>?,
        headerModel: HeaderModel?,
        loading: Boolean?,
        failed: Boolean?
    ) {
        check(!(loading ?: false && failed ?: false)) { "Something is wrong" }
        if (headerModel != null)
            headerCard {
                id("header")
                headerData(headerModel)
            }
        articles?.forEach {
            articleRow {
                id(it.title)
                articleData(it)
                clickListener { articleModel ->
                    this@ArticlesController.onArticleClick(articleModel)
                }
                onBind { _, _, position ->
                    this@ArticlesController.pageLoadingHandler.checkForNewPage(
                        position,
                        articles.size
                    )
                }
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
                retryClickListener(View.OnClickListener { this@ArticlesController.retryClickListener.invoke() })
                spanSizeOverride { totalSpanCount, _, _ -> totalSpanCount }
            }
        }
    }
}
