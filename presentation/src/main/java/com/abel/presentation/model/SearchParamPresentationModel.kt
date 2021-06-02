package com.abel.presentation.model

import android.os.Parcelable
import com.abel.common.model.ArticleCategory
import com.abel.common.model.SearchParam
import com.abel.common.model.SortType
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SearchParamPresentationModel(
    val category: String?,
    val query: String?,
    val sortType: String?,
    val page: Int?
) : Parcelable

fun SearchParam.toSearchParamPresentationModel() =
    SearchParamPresentationModel(category, query, sortType.toString(), page)

fun SearchParamPresentationModel.toSearchParam() =
    SearchParam(category, query, SortType.from(sortType), page)

fun ArticleCategory.toSearchParamPresentationModel() = SearchParamPresentationModel(
    if (isOnApi) label else null,
    if (!isOnApi) label else null,
    null,
    1
)
