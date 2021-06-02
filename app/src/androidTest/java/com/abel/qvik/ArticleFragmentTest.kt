package com.abel.qvik

import androidx.lifecycle.MutableLiveData
import androidx.test.rule.ActivityTestRule
import com.abel.presentation.baseViewModel
import com.abel.presentation.model.ArticleModel
import com.abel.presentation.stateObjects.ArticleState
import com.abel.presentation.viewmodel.ArticleViewModel
import com.abel.qvik.activity.HomeActivity
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest

@ExperimentalCoroutinesApi
class ArticleFragmentTest : KoinTest {
    @get:Rule
    val activityTestRule = object : ActivityTestRule<HomeActivity>(
        HomeActivity::class.java, false,
        true
    ) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            every { viewModel.viewStates } returns viewState
            loadKoinModules(module(override = true) {
                baseViewModel(override = true) { viewModel }
            })
        }
    }

    private val viewModel = mockk<ArticleViewModel>(relaxUnitFun = true)

    private val viewState = MutableLiveData<ArticleState>()

    @Test
    fun shouldRenderUninitializedState() {
        viewState.postValue(ArticleState.Loading)
        R.id.animationView.checkVisible()
        R.id.toolbar.checkExists()
    }

    @Test
    fun shouldRenderLoadingState() {
        val article = ArticleModel("", "", "", "", "", "")
        viewState.postValue(ArticleState.ArticleLoaded(article))
        R.id.animationView.checkVisible()
        R.id.toolbar.checkExists()
    }
}
