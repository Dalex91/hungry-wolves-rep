package com.example.hungrywolves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hungrywolves.model.SplashModel
import com.example.hungrywolves.model.SplashScreenViewModel


class SplashActivity : AppCompatActivity() {
    lateinit var viewModelSplashScreen : SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        
        supportActionBar?.hide()
        initViewModel()
        observeSplashLiveData()
    }

    private fun initViewModel() {
        viewModelSplashScreen = ViewModelProvider(this).get(SplashScreenViewModel::class.java)
    }

    private fun observeSplashLiveData() {
        viewModelSplashScreen.initSplashScreen()
        val observer = Observer<SplashModel> {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        viewModelSplashScreen.liveData.observe(this, observer)
    }
}