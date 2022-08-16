package com.example.hungrywolves

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hungrywolves.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        Log.d("ActivityTag", "I'm here")
        setContentView(R.layout.activity_main)
        setupNav()
    }

    private fun setupNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.menuNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            if(destination.id != R.id.home_screen_fragment) {
                hideBottomBar()
            }
        }
    }

    private fun hideBottomBar(){
        findViewById<View>(R.id.menu_navigation)?.visibility = View.INVISIBLE
    }
}