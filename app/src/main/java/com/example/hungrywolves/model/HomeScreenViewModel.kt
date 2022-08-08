package com.example.hungrywolves.model

import android.util.Log
import androidx.core.net.toUri
import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.load
import com.example.hungrywolves.R
import com.example.hungrywolves.network.*
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeScreenViewModel : ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories : LiveData<List<Category>> = _categories

    private val _meals = MutableLiveData<List<Meal>>()
    val meals : LiveData<List<Meal>> = _meals

    init {
        getCategories()
        getMeals()
    }

    private fun getMeals(category : String = "Beef") {
        viewModelScope.launch {
            try {
                _meals.value = MealsApi.retrofitService.getMeals(category).meals
            }catch (e : Exception) {
                Log.d("mes", "${e.message}")
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            try {
                _categories.value = CategoryApi.retrofitService.getCategories().categories
            }catch (e : Exception) {
                Log.d("mes", "${e.message}")
            }
            categories.value?.get(0)?.selected = true
        }
    }


    fun filterCategories(category: Category) {
        _categories.value?.map {
            it.selected = false
            if(it.name == category.name)
                it.selected = true
            it
        }
        getMeals(category.name)
    }
}