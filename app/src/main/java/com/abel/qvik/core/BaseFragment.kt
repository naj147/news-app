package com.abel.qvik.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.abel.presentation.BaseViewModel
import com.abel.presentation.BaseViewState
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VM : BaseViewModel<VS>, VS : BaseViewState, VB : ViewBinding>(clazz: KClass<VM>) :
    Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    protected val viewmodel by viewModel(clazz = clazz)
    abstract fun render(viewState: VS)

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    protected fun initSupportActionBar(toolbar: Toolbar) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setupWithNavController(findNavController())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

