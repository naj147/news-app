package com.abel.qvik.core

import android.app.Activity
import android.widget.ImageView

class ImageRenderGlideImpl :
    ImageRenderer {
    override fun renderImage(
        imageHolder: ImageView,
        image: String
    ) {
        imageHolder.loadUri(image)
    }
}
