package com.abel.presentation.model

import android.os.Parcelable
import com.abel.common.model.NewsCategory
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeaderModel(
    val title: String,
    val image: String,
    val followers: String
) : Parcelable


fun NewsCategory.toHeaderModel() = HeaderModel(label, imageResource, followers.toString())
