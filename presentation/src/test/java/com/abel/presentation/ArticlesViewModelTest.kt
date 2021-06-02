package com.abel.presentation

import androidx.lifecycle.Observer
import com.abel.common.getDummyPageOfArticles
import com.abel.common.getDummyPageOfArticles2
import com.abel.common.getDummySearchParam
import com.abel.domain.GetArticlesUseCase
import com.abel.presentation.stateObjects.ArticleListState
import com.abel.presentation.viewmodel.ArticleListViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticlesViewModelTest : BaseViewModelTest() {
    private val observer: Observer<ArticleListState> = mockk {
        every { onChanged(any()) } just Runs
    }

    private val getArticlesUseCase: GetArticlesUseCase = mockk()
    private lateinit var viewModel: ArticleListViewModel

    @Test
    fun `should start in Uninitialized state and move to LoadingNextPage`() {
        val pageOfNews = getDummyPageOfArticles()
        val searchParam = getDummySearchParam()
        coEvery {
            getArticlesUseCase.execute(searchParam)
        } coAnswers {
            pageOfNews
        }
        val observer = mockk<Observer<ArticleListState>>(relaxUnitFun = true)
        viewModel = ArticleListViewModel(getArticlesUseCase, backgroundContext)
        viewModel.viewStates.observeForever(observer)


        coVerify(exactly = 1) {
            observer.onChanged(
                ArticleListState.Uninitialized
            )
        }
        viewModel.loadArticles(getDummySearchParam())
        coVerify(exactly = 1) {
            observer.onChanged(
                ArticleListState.LoadingNextPage(null)
            )
        }
    }

    @Test
    fun `should load next page successfully`() {
        val searchParam = getDummySearchParam()
        val firstPage =getDummyPageOfArticles()
        val secondPage = getDummyPageOfArticles2()
        val totalPage = secondPage.copy(
            articles = (firstPage.articles
                ?: emptyList()) + (secondPage.articles
                ?: emptyList())
        )
        with(getArticlesUseCase) {
            coEvery { execute(searchParam) } returns firstPage
            coEvery { execute(searchParam.copy(page = 2)) } returns secondPage
        }
        val observer = mockk<Observer<ArticleListState>>(relaxUnitFun = true)
        viewModel = ArticleListViewModel(getArticlesUseCase, backgroundContext)
        viewModel.viewStates.observeForever(observer)
        viewModel.loadArticles(getDummySearchParam())
        viewModel.loadArticles(getDummySearchParam().copy(page = 2))
        verifyOrder  {
            observer.onChanged(
                ArticleListState.Uninitialized
            )
            observer.onChanged(
                ArticleListState.LoadingNextPage(null)
            )
            observer.onChanged(
                ArticleListState.ArticlesLoaded(firstPage, firstPage.articles?.size ?: 2)
            )
            observer.onChanged(
                ArticleListState.LoadingNextPage(firstPage)
            )
            observer.onChanged(
                ArticleListState.ArticlesLoaded(totalPage, totalPage.articles?.size ?: 2)
            )
        }
    }

    @Test
    fun `should start in Uninitialized state and move to ArticlesLoaded`() {
        val pageOfNews = getDummyPageOfArticles()
        val searchParam = getDummySearchParam()
        coEvery {
            getArticlesUseCase.execute(searchParam)
        } coAnswers {
            pageOfNews
        }
        val observer = mockk<Observer<ArticleListState>>(relaxUnitFun = true)
        viewModel = ArticleListViewModel(getArticlesUseCase, backgroundContext)
        viewModel.viewStates.observeForever(observer)
        viewModel.loadArticles(searchParam)
        coVerify(exactly = 1) {
            observer.onChanged(
                ArticleListState.Uninitialized
            )
            observer.onChanged(
                ArticleListState.LoadingNextPage(null)
            )
            observer.onChanged(
                ArticleListState.ArticlesLoaded(pageOfNews, pageOfNews.articles?.size ?: 2)
            )
        }
    }

    @Test
    fun `should move to LoadingFailed when encountering exception`() {
        val pageOfNews = getDummyPageOfArticles()
        val searchParam = getDummySearchParam()
        val e = RuntimeException("error")
        coEvery {
            getArticlesUseCase.execute(searchParam)
        } coAnswers {
            throw e
        }
        val observer = mockk<Observer<ArticleListState>>(relaxUnitFun = true)
        viewModel = ArticleListViewModel(getArticlesUseCase, backgroundContext)
        viewModel.viewStates.observeForever(observer)
        viewModel.loadArticles(searchParam)
        coVerify(exactly = 1) {
            observer.onChanged(
                ArticleListState.Uninitialized
            )
            observer.onChanged(
                ArticleListState.LoadingNextPage(null)
            )
            observer.onChanged(
                ArticleListState.LoadingFailed(
                    searchParam.category ?: searchParam.query ?: "",
                    0,
                    0,
                    1,
                    e
                )
            )
        }
    }


    override fun tearDown() {
        viewModel.viewStates.removeObserver(observer)
        super.tearDown()
    }
}
