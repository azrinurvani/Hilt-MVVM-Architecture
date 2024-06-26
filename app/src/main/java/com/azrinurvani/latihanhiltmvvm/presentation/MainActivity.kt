package com.azrinurvani.latihanhiltmvvm.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.azrinurvani.latihanhiltmvvm.R
import com.azrinurvani.latihanhiltmvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

//TODO - Step 33
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration.Builder(
            //TODO - Step 59 Change id
            R.id.nav_home,
            R.id.nav_history
        ).build()

        setupActionBarWithNavController(
            navController = navController,
            appBarConfiguration
        )

        binding.bottomNavView.setupWithNavController(navController)

//        setupNavigationComponent()
    }

    private fun setupNavigationComponent(){

    }
}
