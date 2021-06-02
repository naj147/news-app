package com.abel.qvik.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.abel.qvik.R
import com.abel.qvik.activity.HomeActivity

open class EmptyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initSupportActionBar()
    }

    protected fun initSupportActionBar() {
        (activity as HomeActivity).showBottomNav(true)
        (activity as HomeActivity).supportActionBar?.show()
        (activity as HomeActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
