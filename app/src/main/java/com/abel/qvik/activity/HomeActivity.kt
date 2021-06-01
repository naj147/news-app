package com.abel.qvik.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.abel.qvik.R
import com.abel.qvik.core.ImageRenderer
import com.abel.qvik.databinding.ActivityHomeBinding
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity() {
    val imageRenderer: ImageRenderer by inject()
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

    }

    override fun onPostCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onPostCreate(savedInstanceState, persistentState)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.splashFragment, R.id.favoriteFragment))
        setupActionBarWithNavController(
            findNavController(R.id.nav_host_fragment),
            appBarConfiguration
        )
    }

    internal fun showBottomNav(show: Boolean) {
        if (show) {
            binding.bottomNav.visibility = View.VISIBLE
        } else {
            binding.bottomNav.visibility = View.GONE
        }
    }
}
