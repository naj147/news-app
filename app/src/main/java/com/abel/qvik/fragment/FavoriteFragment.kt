package com.abel.qvik.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.abel.presentation.model.toHeaderModel
import com.abel.presentation.model.toSearchParamPresentationModel
import com.abel.presentation.stateObjects.FavoriteCategoryState
import com.abel.presentation.viewmodel.FavoriteCategoryViewModel
import com.abel.qvik.activity.HomeActivity
import com.abel.qvik.controller.FavoriteCategoryController
import com.abel.qvik.core.BaseFragment
import com.abel.qvik.core.ImageRenderer
import com.abel.qvik.databinding.FragmentFavoriteBinding

class FavoriteFragment :
    BaseFragment<FavoriteCategoryViewModel, FavoriteCategoryState, FragmentFavoriteBinding>(
        FavoriteCategoryViewModel::class
    ) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding
        get() = FragmentFavoriteBinding::inflate
    lateinit var imageRenderer: ImageRenderer
    private val favoriteCategoryController by lazy {
        FavoriteCategoryController(
            imageRenderer, { viewmodel.loadCategories() }, { newsCategory ->
                val action =
                    FavoriteFragmentDirections.actionFavoriteFragmentToArticlesFragment(
                        newsCategory.toHeaderModel(), newsCategory.toSearchParamPresentationModel()
                    )
                findNavController().navigate(action)
            }
        )
    }

    override fun render(viewState: FavoriteCategoryState) {
        when (viewState) {
            is FavoriteCategoryState.CategoriesLoaded -> favoriteCategoryController.setData(
                viewState.categories, null, false
            )
            FavoriteCategoryState.Loading -> favoriteCategoryController.setData(
                null, true, null
            )
            is FavoriteCategoryState.LoadingFailed -> favoriteCategoryController.setData(
                null, null, true
            )
            FavoriteCategoryState.Uninitialized -> TODO()
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).showBottomNav(true)
        (activity as HomeActivity).supportActionBar?.show()
        imageRenderer = (activity as HomeActivity).imageRenderer
        binding.recycleView.layoutManager = GridLayoutManager(requireContext(), 2)
        favoriteCategoryController.spanCount = 2
        favoriteCategoryController.setFilterDuplicates(true)
        binding.recycleView.setController(favoriteCategoryController)
        viewmodel.viewStates.observe(viewLifecycleOwner, {
            render(it)
        })
    }
}
