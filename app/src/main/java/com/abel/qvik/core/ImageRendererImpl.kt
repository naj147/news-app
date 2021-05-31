package com.abel.qvik.core

import android.widget.ImageView

class ImageRenderGlideImpl :
    ImageRenderer {
    override fun renderImage(
        imageHolder: ImageView,
        image: String
    ) {
        imageHolder.loadUrl(image)
    }
}
