package com.example.hungrywolves.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolves.network.Meal
import com.example.hungrywolves.network.MealsApi
import kotlinx.coroutines.launch

class SearchScreenViewModel : ViewModel() {
    private val _meals = MutableLiveData<List<Meal>>()
    val meals : LiveData<List<Meal>> = _meals

    suspend fun getSearchedMeals(mealName : String) {
        viewModelScope.launch {
            try {
                _meals.value = MealsApi.retrofitServiceByName.getMeals(mealName).meals
            }catch (e : Exception) {
                Log.d("mes", "${e.message}")
            }
        }
    }
}