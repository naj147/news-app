package com.abel.qvik.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.Nullable
import com.abel.common.model.NewsCategory
import com.abel.qvik.R
import com.abel.qvik.core.ImageRenderer
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import kotlinx.android.synthetic.main.item_card.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT, fullSpan = false)
class CategoryCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.item_card, this)
    }

    var imageRenderer: ImageRenderer? = null
        @CallbackProp
        @Nullable
        set

    var newsCategory: NewsCategory? = null
        @ModelProp
        @Nullable
        set

    var clickListener: OnClickListener? = null
        @CallbackProp
        @Nullable
        set(value) {
            field = value
            this.setOnClickListener(value)
        }

    // Todo: better placeholder
    @AfterPropsSet
    fun renderObject() {
        imageRenderer?.renderImage(
            categoryImg,
            newsCategory?.imageResource ?: ""
        )
        categoryTitle.text = newsCategory?.label
    }
}
