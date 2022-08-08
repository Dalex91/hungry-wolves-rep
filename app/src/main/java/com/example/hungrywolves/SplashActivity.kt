package com.example.hungrywolves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import com.example.hungrywolves.model.SplashScreenViewModel


class SplashActivity : AppCompatActivity() {
    private val viewModelSplashScreen : SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        
        supportActionBar?.hide()
        viewModelSplashScreen.delayIntro(Intent(this, MainActivity::class.java),
            this)
    }
}