package com.abel.domain

import com.abel.common.model.ArticleCategory
import com.abel.domain.repository.CategoryRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetCategoryUseCaseImplTest {
    private val categoryRepository: CategoryRepository = mockk()
    private val getCategoryUseCase = GetCategoryUseCaseImpl(categoryRepository)

    @Test
    fun `should not throw any exception if repository gives answer`() {
        val result = listOf(
            ArticleCategory("https://i.imgur.com/aZGiYMx.png", "321k", "fashion"),
            ArticleCategory("https://i.imgur.com/T96WYUX.png", "120k", "science"),
            ArticleCategory("https://i.imgur.com/sAVZmrZ.png", "1.1m", "auto")
        )
        coEvery {
            categoryRepository.getCategories()
        } coAnswers {
            result
        }

        runBlocking {
            assertThat(getCategoryUseCase.execute())
                .isEqualTo(result)
        }
    }

    @Test(expected = RuntimeException::class)
    fun `should propagate exception from remote data source`() {
        coEvery {
            categoryRepository.getCategories()
        } coAnswers {
            throw RuntimeException("test")
        }

        runBlocking {
            categoryRepository.getCategories()
        }
    }
}
