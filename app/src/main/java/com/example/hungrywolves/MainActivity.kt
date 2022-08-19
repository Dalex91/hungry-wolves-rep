package com.example.hungrywolves

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hungrywolves.databinding.ActivityMainBinding
import com.example.hungrywolves.network.ConnectionLiveData

class MainActivity : AppCompatActivity(), DelegateBottomMenuNavigation{
    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        Log.d("Activity", "I was in main activity")
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        initConnection()

        navController.addOnDestinationChangedListener{_, destination, _ ->
            binding.menuNavigation.apply {
                visibility = when(destination.id) {
                    R.id.home_screen_fragment, R.id.profile_screen_fragment, R.id.favourites_screen_fragment -> View.VISIBLE
                    else -> View.GONE
                }
            }
        }

        binding.menuNavigation.setupWithNavController(navController)
    }

    private fun initConnection() {
        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) {isNetworkAvailable ->
            Log.d("Internet", "$isNetworkAvailable")
            if(isNetworkAvailable == false)
                navController.navigate(R.id.no_internet_screen_fragment)
            else
                navController.navigateUp()
        }
    }

    override fun navToFavourites() {
        navController.navigate(R.id.favourites_screen_fragment)
    }

}