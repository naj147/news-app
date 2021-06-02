package com.abel.data

import com.abel.common.getDummyPageOfArticles
import com.abel.common.getDummySearchParam
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test

class ArticlesRepositoryImplTest {
    private val articlesRemoteDataSource: ArticlesRemoteDataSource = mockk()
    private val newsRepositoryImpl = ArticlesRepositoryImpl(articlesRemoteDataSource)

    @Test
    fun `should not throw any exception if local data source gives answer`() {
        val result = getDummyPageOfArticles()
        coEvery {
            articlesRemoteDataSource.getArticlesWithCategory(getDummySearchParam())
        } coAnswers {
            result
        }

        runBlocking {
            Assertions.assertThat(newsRepositoryImpl.getArticles(getDummySearchParam()))
                .isEqualTo(result)
        }
    }

    @Test(expected = RuntimeException::class)
    fun `should propagate exception from remote data source`() {
        coEvery {
            articlesRemoteDataSource.getArticlesWithCategory(getDummySearchParam())
        } coAnswers {
            throw RuntimeException("error")
        }

        runBlocking {
            newsRepositoryImpl.getArticles(getDummySearchParam())
        }
    }
}
