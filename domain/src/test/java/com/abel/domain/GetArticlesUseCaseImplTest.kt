package com.abel.domain

import com.abel.common.getDummyPageOfArticles
import com.abel.common.getDummySearchParam
import com.abel.domain.repository.ArticlesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test

class GetArticlesUseCaseImplTest {
    private val articlesRepository: ArticlesRepository = mockk()
    private val getNewsUseCase = GetArticlesUseCaseImpl(articlesRepository)

    @Test
    fun `should not throw any exception if repository gives answer`() {
        val result = getDummyPageOfArticles()
        coEvery {
            articlesRepository.getArticles(getDummySearchParam())
        } coAnswers {
            result
        }

        runBlocking {
            Assertions.assertThat(getNewsUseCase.execute(getDummySearchParam()))
                .isEqualTo(result)
        }
    }

    @Test(expected = RuntimeException::class)
    fun `should propagate exception from remote data source`() {
        coEvery {
            articlesRepository.getArticles(getDummySearchParam())
        } coAnswers {
            throw RuntimeException("test")
        }

        runBlocking {
            getNewsUseCase.execute(getDummySearchParam())
        }
    }
}
