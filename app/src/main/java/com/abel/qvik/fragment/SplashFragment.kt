package com.abel.qvik.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.abel.qvik.R
import com.abel.qvik.activity.HomeActivity
import com.abel.qvik.databinding.FragmentSplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        try {
            (activity as HomeActivity).showBottomNav(false)
            (activity as HomeActivity).supportActionBar?.hide()
        } catch (e: ClassCastException) {
            Log.d(this.tag, e.message ?: "Error casting")
        }
        (activity as HomeActivity).supportActionBar?.hide()
        GlobalScope.launch(Dispatchers.Main) {
            delay(4000L)
            findNavController().navigate(R.id.favoriteFragment)
            onDestroy()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
