package com.abel.presentation

import androidx.lifecycle.Observer
import com.abel.common.getDummyArticleCategories
import com.abel.domain.GetCategoryUseCase
import com.abel.presentation.stateObjects.FavoriteCategoryState
import com.abel.presentation.viewmodel.FavoriteCategoryViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.junit.Test

@ExperimentalCoroutinesApi
class FavoriteCategoryViewModelTest : BaseViewModelTest() {
    private val observer: Observer<FavoriteCategoryState> = mockk {
        every { onChanged(any()) } just Runs
    }

    private val getCategoryUseCase: GetCategoryUseCase = mockk()
    private lateinit var viewModel: FavoriteCategoryViewModel

    @Test
    fun `should start in Uninitialized state and move to Loading`() {
        coEvery {
            getCategoryUseCase.execute()
        } coAnswers {
            delay(1000)
            emptyList()
        }
        val observer = mockk<Observer<FavoriteCategoryState>>(relaxUnitFun = true)
        viewModel = FavoriteCategoryViewModel(getCategoryUseCase, backgroundContext)
        viewModel.viewStates.observeForever(observer)

        coVerify(exactly = 1) {
            observer.onChanged(
                FavoriteCategoryState.Uninitialized
            )
        }
    }

    @Test
    fun `should start in Loading state and move to CategoriesLoaded`() {
        coEvery {
            getCategoryUseCase.execute()
        } coAnswers {
            delay(1000)
            getDummyArticleCategories()
        }
        val observer = mockk<Observer<FavoriteCategoryState>>(relaxUnitFun = true)
        viewModel = FavoriteCategoryViewModel(getCategoryUseCase, backgroundContext)
        viewModel.viewStates.observeForever(observer)
        viewModel.loadCategories()
        coVerify(exactly = 1) {
            observer.onChanged(FavoriteCategoryState.Loading)
        }
        backgroundContext.advanceTimeBy(1500)
        coVerify(exactly = 1) {
            observer.onChanged(FavoriteCategoryState.CategoriesLoaded(getDummyArticleCategories()))
        }
    }

    @Test
    fun `should loadEmptyList state and move to CategoriesLoaded`() {
        coEvery {
            getCategoryUseCase.execute()
        } coAnswers {
            throw RuntimeException("error")
        }
        val observer = mockk<Observer<FavoriteCategoryState>>(relaxUnitFun = true)
        viewModel = FavoriteCategoryViewModel(getCategoryUseCase, backgroundContext)
        viewModel.viewStates.observeForever(observer)
        viewModel.loadCategories()
        coVerify(exactly = 1) {
            observer.onChanged(FavoriteCategoryState.CategoriesLoaded(emptyList()))
        }
    }

    override fun tearDown() {
        viewModel.viewStates.removeObserver(observer)
        super.tearDown()
    }
}
