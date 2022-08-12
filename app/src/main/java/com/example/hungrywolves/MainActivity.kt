package com.example.hungrywolves

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }

    fun setupNav() {
        val navController = findNavController(androidx.navigation.fragment.R.id.nav_host_fragment_container)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            if(destination.id == R.id.search_screen_fragment)
               hideBottomBar()
        }
    }

    fun hideBottomBar(){
        findViewById<View>(R.id.menu_navigation)?.visibility = View.INVISIBLE
    }
}