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
    ): PageOfNews =
        serviceFunc({
            newsService.fetchNewsByCategory(
                it.query,
                it.category,
                it.page
            ).ToPageOfNews(searchParam.page)
        }, searchParam)


    override suspend fun getLatestNews(searchParam: SearchParam): PageOfNews =
        serviceFunc({
            newsService.fetchNewsBySortParam(
                it.sortType.toString(), it.page
            ).ToPageOfNews(searchParam.page)
        }, searchParam)

    private suspend fun serviceFunc(
        fn: suspend (search: SearchParam) -> PageOfNews,
        searchParam: SearchParam
    ) = try {
        withContext(ioContext) {
            fn(searchParam)
        }
    } catch (j: JsonDataException) {
        // Moshi can double wrap JsonDataException
        fun JsonDataException.getCause(): Throwable {
            return (cause as? JsonDataException)?.getCause() ?: cause ?: this
        }
        throw j.getCause()
    }
}
