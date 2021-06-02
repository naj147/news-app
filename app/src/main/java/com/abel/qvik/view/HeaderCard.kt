package com.abel.qvik.view

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import com.abel.presentation.model.HeaderModel
import com.abel.qvik.R
import com.abel.qvik.core.KotlinEpoxyHolder
import com.abel.qvik.core.loadUrl
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder


@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_header)
internal abstract class HeaderCard : EpoxyModelWithHolder<HeaderCardHolder>() {
    @EpoxyAttribute()
    lateinit var headerData: HeaderModel

    @SuppressLint("SetTextI18n")
    override fun bind(holder: HeaderCardHolder) {
        super.bind(holder)
        holder.headerTitle.text = headerData.title + " Channel"
        holder.headerImage.loadUrl(headerData.image)
        holder.followers.text = headerData.followers + " Followers"
    }
}

class HeaderCardHolder : KotlinEpoxyHolder() {
    val headerTitle by bind<TextView>(R.id.headerTitle)
    val headerImage by bind<ImageView>(R.id.headerImg)
    val followers by bind<TextView>(R.id.followers)
}
