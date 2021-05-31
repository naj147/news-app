package com.abel.qvik.core

import android.view.View

internal fun View.hide(hide: Boolean) {
    if (!hide) this.visibility = View.VISIBLE else this.visibility = View.GONE
}
