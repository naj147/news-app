package com.abel.remote

import com.abel.common.model.PageOfArticles
import com.abel.common.model.SearchParam
import com.abel.data.ArticlesRemoteDataSource
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Class used to get articles [ArticlesRemoteDataSource] using Retrofit Service
 * @property NewsService withing a
 * @property CoroutineContext
 */
class ArticlesRemoteDataSourceImpl(
    private val newsService: NewsService,
    private val ioContext: CoroutineContext
) : ArticlesRemoteDataSource {

    override suspend fun getArticlesWithCategory(
        searchParam: SearchParam
    ): PageOfArticles =
        serviceFunc({
            newsService.fetchNewsByCategory(
                it.query,
                it.category,
                it.page
            ).toPageOfArticles(searchParam.page, searchParam.category ?: "")
        }, searchParam)

//    override suspend fun getLatestNews(searchParam: SearchParam): PageOfNews =
//        serviceFunc({
//            newsService.fetchNewsBySortParam(
//                it.sortType.toString(), it.page
//            ).ToPageOfNews(searchParam.page, searchParam.category ?: "")
//        }, searchParam)


    /**
        function that wraps the retrofit service calls inside a coroutine context
        used as I thought I'll expand to include other retrofit service [com.abel.remote.NewsService] calls like the one commented above in which
        this function cuts down on boilerplate
     */
    private suspend fun serviceFunc(
        fn: suspend (search: SearchParam) -> PageOfArticles,
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
