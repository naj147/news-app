package com.abel.qvik.view

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.abel.presentation.model.ArticleModel
import com.abel.qvik.R
import com.abel.qvik.core.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@SuppressLint("NonConstantResourceId")

@EpoxyModelClass(layout = R.layout.item_article)
internal abstract class ArticleRow : EpoxyModelWithHolder<ArticleRowHolder>() {
    @EpoxyAttribute()
    lateinit var articleData: ArticleModel

    @EpoxyAttribute
    lateinit var clickListener: (ArticleModel) -> Unit

    override fun bind(holder: ArticleRowHolder) {
        super.bind(holder)
        holder.container.apply {
            setOnClickListener {
                clickListener(articleData)
            }
        }
        holder.articleTitle.text = articleData.title
        holder.articlePublisher.text = articleData.publisher
        holder.articleTime.text = articleData.date
    }
}

class ArticleRowHolder : KotlinEpoxyHolder() {
    val articleTitle by bind<TextView>(R.id.articleTitle)
    val articlePublisher by bind<TextView>(R.id.articlePublisher)
    val articleTime by bind<TextView>(R.id.articleTime)
    val container by bind<View>(R.id.container)
}
