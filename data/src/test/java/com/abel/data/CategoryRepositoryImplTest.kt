package com.abel.data

import com.abel.common.getDummyArticleCategories
import com.abel.common.getDummyPageOfArticles
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CategoryRepositoryImplTest {
    private val categoryCacheDataSource: CategoryCacheDataSource = mockk()
    private val categoryRepositoryImpl = CategoryRepositoryImpl(categoryCacheDataSource)

    @Test
    fun `should not throw any exception if local data source gives answer`() {
        val result = getDummyArticleCategories()
        coEvery {
            categoryCacheDataSource.getCategories()
        } coAnswers {
            result
        }

        runBlocking {
            assertThat(categoryRepositoryImpl.getCategories())
                .isEqualTo(result)
        }
    }

    @Test(expected = RuntimeException::class)
    fun `should propagate exception from remote data source`() {
        coEvery {
            categoryCacheDataSource.getCategories()
        } coAnswers {
            throw RuntimeException("error")
        }

        runBlocking {
            categoryRepositoryImpl.getCategories()
        }
    }
}
