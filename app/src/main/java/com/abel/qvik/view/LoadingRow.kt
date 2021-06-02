package com.abel.qvik.view

import android.annotation.SuppressLint
import com.abel.qvik.R
import com.abel.qvik.core.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_loading)
abstract class LoadingRow: EpoxyModelWithHolder<LoadingHolder>()

class LoadingHolder: KotlinEpoxyHolder()
