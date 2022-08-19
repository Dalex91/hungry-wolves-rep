package com.example.hungrywolves

import android.app.Application
import com.orhanobut.hawk.Hawk

class InitApp : Application(){
    companion object {
        const val USERNAME = "bogdan"
    }

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
    }
}