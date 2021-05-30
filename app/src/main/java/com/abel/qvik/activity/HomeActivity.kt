package com.abel.qvik.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.abel.qvik.core.ImageRenderer
import com.abel.qvik.databinding.ActivityHomeBinding
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

class HomeActivity : AppCompatActivity(){
    val imageRenderer: ImageRenderer by inject()
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    internal fun showBottomNav(show: Boolean) {
        if (show) {
            binding.bottomNav.visibility = View.VISIBLE
        } else {
            binding.bottomNav.visibility = View.GONE
        }
    }
}
