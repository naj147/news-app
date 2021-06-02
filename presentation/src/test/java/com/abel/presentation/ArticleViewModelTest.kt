package com.abel.presentation

import androidx.lifecycle.Observer
import com.abel.presentation.stateObjects.ArticleState
import com.abel.presentation.viewmodel.ArticleViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticleViewModelTest : BaseViewModelTest() {
    private val observer: Observer<ArticleState> = mockk {
        every { onChanged(any()) } just Runs
    }

    private lateinit var viewModel: ArticleViewModel

    @Test
    fun `should start in Uninitialized state and move to Loading then Loaded`() {
        val observer = mockk<Observer<ArticleState>>(relaxUnitFun = true)
        viewModel = ArticleViewModel()
        viewModel.viewStates.observeForever(observer)
        viewModel.loadArticle(getDummyArticle())

        coVerify(exactly = 1) {
            observer.onChanged(
                ArticleState.Uninitialized
            )
            observer.onChanged(
                ArticleState.Loading
            )
            observer.onChanged(
                ArticleState.ArticleLoaded(getDummyArticle())
            )
        }
    }

    override fun tearDown() {
        viewModel.viewStates.removeObserver(observer)
        super.tearDown()
    }
}
