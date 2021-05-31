package com.abel.qvik.core

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.abel.qvik.R
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun GlideRequest<Drawable>.configure(
    centerCrop: Boolean = false,
    cache: Boolean = true,
    noFade: Boolean = false,
    noPlaceholder: Boolean = false,
    errorImage: Drawable? = null,
    onSuccess: (() -> Unit)? = null,
    onError: (() -> Unit)? = null
) = this.diskCacheStrategy(if (cache) DiskCacheStrategy.AUTOMATIC else DiskCacheStrategy.NONE)
    .skipMemoryCache(!cache)
    .listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onError?.invoke()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onSuccess?.invoke()
            return false
        }
    })
    .apply {
        errorImage?.let { error(it) }
        if (centerCrop) centerCrop() else centerInside()
        if (!noPlaceholder) placeholder(R.drawable.overlay)
        if (!noFade) transition(DrawableTransitionOptions().crossFade())
    }

fun ImageView.loadUrl(
    url: String?,
    cache: Boolean = true,
    noFade: Boolean = false,
    noPlaceholder: Boolean = false,
    errorImage: Drawable? = null,
    onSuccess: (() -> Unit)? = null,
    onError: (() -> Unit)? = null
) {
    GlideApp.with(context)
        .load(url)
        .configure(
            true,
            cache,
            noFade,
            noPlaceholder,
            errorImage,
            onSuccess,
            onError
        )
        .into(this)
}
