package com.example.mtshomework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mtshomework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var fragment: MovieDetailsFragment? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigation = binding.bottomNavigation
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().replace(R.id.container_view, MovieDetailsFragment(), TAG_FRAGMENT).commit()
        else
            fragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT) as? MovieDetailsFragment
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container_view, MovieDetailsFragment(), TAG_FRAGMENT).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container_view, ProfileFragment(), TAG_FRAGMENT).commit()
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }
}

private const val TAG_FRAGMENT = "FRAGMENT"