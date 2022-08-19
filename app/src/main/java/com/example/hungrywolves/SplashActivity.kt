package com.example.hungrywolves

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hungrywolves.databinding.SplashScreenBinding
import com.example.hungrywolves.model.SplashScreenViewModel


class SplashActivity : AppCompatActivity() {
    private lateinit var viewModelSplashScreen: SplashScreenViewModel
    private lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        val observer = Observer<Boolean> {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        viewModelSplashScreen.liveData.observe(this, observer)
    }
}