package com.example.hungrywolves.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolves.network.api.ProfileApi
import com.example.hungrywolves.models.User
import kotlinx.coroutines.launch

class ProfileScreenViewModel : ViewModel() {
    private val _user = MutableLiveData<User?>()
    val user : LiveData<User?> = _user

    fun getProfileUser() {
        viewModelScope.launch {
            try {
                _user.value = ProfileApi.retrofitService.getUserProfile()
            }
            catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }
}