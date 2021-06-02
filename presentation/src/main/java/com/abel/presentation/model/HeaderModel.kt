package com.abel.presentation.model

import android.os.Parcelable
import com.abel.common.model.ArticleCategory
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HeaderModel(
    val title: String,
    val image: String,
    val followers: String
) : Parcelable


fun ArticleCategory.toHeaderModel() = HeaderModel(label, imageResource, followers.toString())
