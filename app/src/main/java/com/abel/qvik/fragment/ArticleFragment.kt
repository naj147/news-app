package com.abel.qvik.fragment

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.abel.presentation.model.ArticleModel
import com.abel.presentation.stateObjects.ArticleState
import com.abel.presentation.viewmodel.ArticleViewModel
import com.abel.qvik.R
import com.abel.qvik.activity.HomeActivity
import com.abel.qvik.core.BaseFragment
import com.abel.qvik.core.hide
import com.abel.qvik.core.loadUrl
import com.abel.qvik.databinding.FragmentArticleBinding
import com.google.android.material.snackbar.Snackbar

class ArticleFragment : BaseFragment<ArticleViewModel, ArticleState, FragmentArticleBinding>(
    ArticleViewModel::class
) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentArticleBinding
        get() = FragmentArticleBinding::inflate
    private var snackBar: Snackbar? = null
    private val args: ArticleFragmentArgs by navArgs()
    private val article by lazy {
        args.articleModel
    }

    override fun render(viewState: ArticleState) {
        snackBar?.dismiss()
        when (viewState) {
            is ArticleState.ArticleLoaded -> {
                hideLoading(true)
                setupUI(viewState.article)
            }
            is ArticleState.Loading -> hideLoading(false)
            is ArticleState.LoadingFailed -> {
                snackBar = Snackbar
                    .make(
                        requireView(),
                        getString(R.string.error_msg),
                        Snackbar.LENGTH_INDEFINITE
                    )
                    .setAction(R.string.retry) { article?.let { viewmodel.loadArticle(it) } }
                    .also { it.show() }
            }
            ArticleState.Uninitialized -> article?.let { viewmodel.loadArticle(it) }
        }
    }

    private fun hideLoading(hide: Boolean) {
        binding.animationView.hide(hide)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).showBottomNav(false)
        (activity as HomeActivity).supportActionBar?.show()
        viewmodel.viewStates.observe(viewLifecycleOwner, {
            render(it)
        })
    }

    override fun onDestroyView() {
        snackBar?.dismiss()
        super.onDestroyView()
    }

    private fun setupUI(article: ArticleModel) {
        binding.content.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(article.content, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(article.content)
        }
        binding.articleTitle.text = article.title
        binding.articlePublisher.text = article.publisher
        binding.articleTime.text = article.date
        binding.articleCategory.text = article.category
        binding.categoryImg.loadUrl(article.imageUrl)
    }
}
