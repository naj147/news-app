package com.abel.qvik.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.abel.presentation.BaseViewModel
import com.abel.presentation.BaseViewState
import com.abel.qvik.R
import com.abel.qvik.activity.HomeActivity
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
        initSupportActionBar()
        return binding.root
    }

    protected fun initSupportActionBar() {
        (activity as HomeActivity).showBottomNav(true)
        (activity as HomeActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as HomeActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
      if (id == R.id.home) {
          findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

