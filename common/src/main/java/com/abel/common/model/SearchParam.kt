package com.abel.common.model

import java.util.*

data class SearchParam(
    val category: String?,
    val query: String?,
    val sortType: SortType? = SortType.RELEVANCY,
    val page: Int?
)

enum class SortType(sort: String) {
    POPULARITY("popularity"),
    RELEVANCY("relevancy"),
    PUBLISHED_AT("publishedAt");

    companion object {
        fun from(findValue: String?): SortType {
            return if (findValue.isNullOrEmpty())
                RELEVANCY
            else
                values().first { it.name.toLowerCase(Locale.getDefault()) == findValue }
        }
    }
}
