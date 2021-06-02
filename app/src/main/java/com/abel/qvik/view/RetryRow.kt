package com.abel.qvik.view

import android.view.View
import android.widget.TextView
import com.abel.qvik.R
import com.abel.qvik.core.KotlinEpoxyHolder
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_retry)
internal abstract class RetryRow : EpoxyModelWithHolder<RetryHolder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var retryClickListener: View.OnClickListener

    override fun bind(holder: RetryHolder) {
        super.bind(holder)
        holder.retryButton.setOnClickListener(retryClickListener)
    }
}

class RetryHolder : KotlinEpoxyHolder() {
    val retryButton by bind<TextView>(R.id.retryButton)
}
