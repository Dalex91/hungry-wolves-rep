package com.example.hungrywolves.activities.splash


import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel(application: Application) : AndroidViewModel(application) {

    var liveData: MutableLiveData<Boolean> = MutableLiveData()

    companion object {
        const val TIME_FOR_LOADING : Long = 3000
    }

    fun initSplashScreen() {
        viewModelScope.launch {
            delay(TIME_FOR_LOADING)
            updateLiveData()
        }
    }

    private fun updateLiveData() {
        val splashModel = true
        liveData.value = splashModel
    }
}
