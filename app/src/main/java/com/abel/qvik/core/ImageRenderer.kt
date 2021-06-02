package com.abel.qvik.core

import android.widget.ImageView
import androidx.annotation.DrawableRes

// Interface used to abstract our Image processing library from our Views (show proof)
interface ImageRenderer {
    fun renderImage(
        imageHolder: ImageView,
        image: String
    ): Unit
}
