package com.abel.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.abel.common.model.PageOfNews
import com.abel.common.model.SearchParam
import com.abel.domain.GetNewsUseCase
import com.abel.presentation.BaseViewModel
import com.abel.presentation.stateObjects.ArticleListState
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ArticleListViewModel(
    private val getArticlesUseCase: GetNewsUseCase,
    private val backgroundContext: CoroutineContext = Dispatchers.Default
) : BaseViewModel<ArticleListState>() {
    private var nextPageJob: Job? = null

    init {
        _viewStates.value = ArticleListState.Uninitialized
        nextPageJob?.cancel()
    }

    fun loadArticles(searchParam: SearchParam) {
        with(viewStates.value) {
            if (this is ArticleListState.ArticlesLoaded || this is ArticleListState.LoadingFailed) {
                val resultsSoFar = when (this) {
                    is ArticleListState.LoadingFailed -> this.resultsSoFar
                    is ArticleListState.ArticlesLoaded -> this.resultsSoFar + (this.pageOfNews?.articles?.size
                        ?: 0)
                    else -> throw IllegalStateException("The state ${this.javaClass.simpleName} is unexpected")
                }
                val totalResults = when (this) {
                    is ArticleListState.LoadingFailed -> this.totalResults
                    is ArticleListState.ArticlesLoaded -> this.pageOfNews?.totalResults ?: 0
                    else -> throw IllegalStateException("The state ${this.javaClass.simpleName} is unexpected")
                }
                val nextPage = when (this) {
                    is ArticleListState.LoadingFailed -> this.totalPages
                    is ArticleListState.ArticlesLoaded -> (this.pageOfNews?.page ?: 1) + 1
                    else -> throw IllegalStateException("The state ${this.javaClass.simpleName} is unexpected")
                }
                val articlesLoaded = when (this) {
                    is ArticleListState.ArticlesLoaded -> this.pageOfNews
                    is ArticleListState.LoadingFailed -> null
                    else -> throw IllegalStateException("The state ${this.javaClass.simpleName} is unexpected")
                }
                if (totalResults < resultsSoFar) {
                    return@with
                }
                loadArticleJob(searchParam, articlesLoaded, nextPage, resultsSoFar, totalResults)
            } else {
                if (this is ArticleListState.Uninitialized) {
                    loadArticleJob(searchParam, null, 1, 0, 0)
                }
            }
        }
    }

    private fun loadArticleJob(
        searchParam: SearchParam,
        articlesLoaded: PageOfNews? = null,
        nextPage: Int,
        resultsSoFar: Int,
        totalResults: Int
    ) {
        _viewStates.value = ArticleListState.LoadingNextPage(articlesLoaded)
        nextPageJob = viewModelScope.launch {
            try {
                _viewStates.value =
                    withContext(backgroundContext) {
                        val nextPageArticles =
                            getArticlesUseCase.execute(searchParam.copy(page = nextPage))
                        ArticleListState.ArticlesLoaded(
                            nextPageArticles.copy(
                                articles = (articlesLoaded?.articles
                                    ?: emptyList()) + (nextPageArticles.articles
                                    ?: emptyList())
                            ),
                            (nextPageArticles.articles?.size ?: 0) + resultsSoFar
                        )
                    }
            } catch (ignored: CancellationException) {

            } catch (e: Throwable) {
                // We might sometimes face Http code 426 cause I need to upgrade my version of the API to a paid one
                _viewStates.value = ArticleListState.LoadingFailed(
                    searchParam.category ?: searchParam.query ?: "",
                    resultsSoFar,
                    totalResults,
                    nextPage,
                    e
                )
            }
        }
    }

    override fun onCleared() {
        nextPageJob?.cancel()
        super.onCleared()
    }
}
