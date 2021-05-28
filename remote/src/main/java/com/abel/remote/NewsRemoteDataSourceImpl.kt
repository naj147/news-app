package com.abel.remote

import com.abel.common.model.PageOfNews
import com.abel.common.model.SearchParam
import com.abel.data.NewsRemoteDataSource
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class NewsRemoteDataSourceImpl(
    private val newsService: NewsService,
    private val ioContext: CoroutineContext
) : NewsRemoteDataSource {
    override suspend fun getNewsWithCategory(
        searchParam: SearchParam
    ): PageOfNews = try {
        withContext(ioContext) {
            newsService.fetchNewsByCategory(
                searchParam.query,
                searchParam.category,
                searchParam.page
            ).ToPageOfNews(searchParam.page)
        }
    } catch (j: JsonDataException) {
        // Moshi can double wrap JsonDataException
        fun JsonDataException.getCause(): Throwable {
            return (cause as? JsonDataException)?.getCause() ?: cause ?: this
        }
        throw j.getCause
    }
}
