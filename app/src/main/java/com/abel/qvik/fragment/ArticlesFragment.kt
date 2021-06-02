package com.abel.qvik.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abel.presentation.model.toArticleModel
import com.abel.presentation.model.toSearchParam
import com.abel.presentation.stateObjects.ArticleListState
import com.abel.presentation.viewmodel.ArticleListViewModel
import com.abel.qvik.R
import com.abel.qvik.activity.HomeActivity
import com.abel.qvik.controller.ArticlesController
import com.abel.qvik.core.BaseFragment
import com.abel.qvik.core.PageLoadingHandler
import com.abel.qvik.databinding.FragmentArticlesBinding
import com.google.android.material.snackbar.Snackbar

class ArticlesFragment :
    BaseFragment<ArticleListViewModel, ArticleListState, FragmentArticlesBinding>(
        ArticleListViewModel::class
    ) {
    private var snackBar: Snackbar? = null
    private val args: ArticlesFragmentArgs by navArgs()
    private val header by lazy {
        args.header
    }
    private val searchParam by lazy {
        args.searchParam.toSearchParam()
    }
    private val pageLoadingHandler by lazy {
        object : PageLoadingHandler() {
            override fun onLoadNextPage() {
                viewmodel.loadArticles(searchParam)
            }
        }
    }
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArticlesBinding
        get() = FragmentArticlesBinding::inflate
    private val articlesController by lazy {
        ArticlesController(
            pageLoadingHandler,
            { articleModel ->
                val action =
                    ArticlesFragmentDirections.actionArticlesFragmentToArticleFragment(
                        articleModel
                    )
                findNavController().navigate(action)
            }, { viewmodel.loadArticles(args.searchParam.toSearchParam()) })
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).showBottomNav(false)
        (activity as HomeActivity).supportActionBar?.show()
        binding.recycleView.setController(articlesController)
        viewmodel.viewStates.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    override fun render(viewState: ArticleListState) {
        snackBar?.dismiss()
        when (viewState) {
            is ArticleListState.ArticlesLoaded -> {
                articlesController.setData(
                    viewState.pageOfArticles?.articles?.map { it.toArticleModel() }, header,
                    false,
                    false
                )
                pageLoadingHandler.hasNextPage =
                    viewState.resultsSoFar < (viewState.pageOfArticles?.totalResults) ?: 0
                if (viewState.resultsSoFar == 0) {
                    binding.recycleView.smoothScrollToPosition(0)
                }
            }
            is ArticleListState.LoadingFailed -> {
                snackBar = Snackbar
                    .make(
                        requireView(),
                        getString(R.string.error_msg),
                        Snackbar.LENGTH_INDEFINITE
                    )
                    .setAction(R.string.retry) { viewmodel.loadArticles(args.searchParam.toSearchParam()) }
                    .also { it.show() }

                articlesController.setData(
                    null, header,
                    true,
                    false
                )
            }

            is ArticleListState.LoadingNextPage -> articlesController.setData(
                viewState.pageOfArticles?.articles?.map { it.toArticleModel() }, header,
                true,
                false
            )
            ArticleListState.Uninitialized -> viewmodel.loadArticles(args.searchParam.toSearchParam())
        }
    }

    override fun onDestroyView() {
        snackBar?.dismiss()
        super.onDestroyView()
    }
}
