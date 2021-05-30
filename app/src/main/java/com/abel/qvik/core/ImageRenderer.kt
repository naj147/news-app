package com.abel.qvik.core

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageRenderer {
    fun renderImage(
        imageHolder: ImageView,
        image: String
    ): Unit
}
