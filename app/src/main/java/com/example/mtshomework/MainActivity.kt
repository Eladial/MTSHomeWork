package com.example.mtshomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.mtshomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigation = binding.bottomNavigation
        navController.navigate(R.id.moviesFragment)
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.moviesFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }

    }

}

private const val TAG_FRAGMENT = "FRAGMENT"