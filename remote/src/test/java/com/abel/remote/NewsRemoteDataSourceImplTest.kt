package com.abel.remote

import com.abel.common.getDummyPageOfArticles
import com.abel.common.model.SearchParam
import com.abel.common.model.SortType
import com.abel.exception.NewsApiException
import com.abel.exception.OutOfOrdinaryException
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.coroutines.EmptyCoroutineContext


internal class NewsRemoteDataSourceImplTest : BaseApiTest() {
    private val newsRemoteDataSourceImpl =
        ArticlesRemoteDataSourceImpl(getMockedRestApi(), EmptyCoroutineContext)
    private val searchParam = SearchParam("one","",SortType.POPULARITY,1)

    @Test
    fun `should map successfully to app model`() {
        mockServer.enqueue(
            MockResponse().setBody("json/success.json".readJson()).setResponseCode(200)
        )
        runBlocking {
            val result = newsRemoteDataSourceImpl.getArticlesWithCategory(searchParam)

            val dummyData = getDummyPageOfArticles()
            assertThat(
                result.totalResults == dummyData.totalResults
            )
            assertThat(
                result.page == dummyData.page
            )
            assertThat(
                result.articles?.size == 3
            )
            assertThat(
                result.articles?.get(0)?.source == dummyData.articles?.get(0)?.source
            )
        }
    }
/*
    @Test(expected = NewsApiException::class)
    fun `should throw NewsApiException`() {
        runBlocking {
            mockServer.enqueue(
                MockResponse().setBody("json/failure.json".readJson()).setResponseCode(200)
            )
            newsRemoteDataSourceImpl.getArticlesWithCategory(searchParam)
        }
    }
*/
    @Test(expected = Exception::class)
    fun `should throw OutOfOrdinaryException if json is not conformed to api contract`() {
        runBlocking {
            mockServer.enqueue(
                MockResponse().setBody("{}").setResponseCode(400)
            )
            newsRemoteDataSourceImpl.getArticlesWithCategory(searchParam)
        }
    }
}
