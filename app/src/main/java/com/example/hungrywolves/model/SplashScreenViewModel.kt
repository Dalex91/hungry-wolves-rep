package com.example.hungrywolves.model

import android.app.Activity
import android.content.Intent
import android.os.Handler
import androidx.lifecycle.ViewModel

class SplashScreenViewModel : ViewModel() {

    companion object {
        const val TIME_FOR_LOADING : Long = 3000
    }

    fun delayIntro(intent: Intent, activity: Activity) {
        Handler().postDelayed({
            activity.startActivity(intent)
            activity.finish()
        }, TIME_FOR_LOADING)
    }
}
