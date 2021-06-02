package com.abel.qvik

import androidx.lifecycle.MutableLiveData
import androidx.test.rule.ActivityTestRule
import com.abel.presentation.baseViewModel
import com.abel.presentation.stateObjects.FavoriteCategoryState
import com.abel.presentation.viewmodel.FavoriteCategoryViewModel
import com.abel.qvik.activity.HomeActivity
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest

class FavoriteFragmentTest : KoinTest {
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

    private val viewModel = mockk<FavoriteCategoryViewModel>(relaxUnitFun = true)

    private val viewState = MutableLiveData<FavoriteCategoryState>()

    @Test
    fun shouldRenderUninitializedState() {
        viewState.postValue(FavoriteCategoryState.Uninitialized)

        R.id.recycleView.checkInvisible()
        R.id.toolbar.checkVisible()
    }

    @Test
    fun shouldRenderLoadingState() {
        viewState.postValue(FavoriteCategoryState.Loading)
        R.id.recycleView.checkInvisible()
        R.id.toolbar.checkVisible()
    }
}
