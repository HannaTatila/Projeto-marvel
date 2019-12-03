package com.example.marvel.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.marvel.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ComicsActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    //replaceFragment(ProfileFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_settings -> {
                    //replaceFragment(SettingsFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comics)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bnv_navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        replaceFragment(HomeFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

}