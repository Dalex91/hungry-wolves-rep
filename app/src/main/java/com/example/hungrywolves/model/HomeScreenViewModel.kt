package com.example.hungrywolves.model

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolves.network.*
import com.example.hungrywolves.network.data_model.Category
import com.example.hungrywolves.network.data_model.Meal
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeScreenViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories : LiveData<List<Category>> = _categories

    private val _meals = MutableLiveData<List<Meal>?>()
    val meals : LiveData<List<Meal>?> = _meals

    init {
        getCategories()
    }

    private fun getMeals(category : String) {
        viewModelScope.launch {
            try {
                _meals.value = MealsApi.retrofitServiceMeal.getMealsByCategory(category).meals
            }catch (e : Exception) {
                Log.d("mes", "${e.message}")
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            try {
                _categories.value = CategoryApi.retrofitService.getCategories().categories
                    .mapIndexed { index, category ->
                    category.copy(selected = (index == 0))
                }
            }catch (e : Exception) {
                Log.d("mes", "${e.message}")
            }
            _categories.value?.firstOrNull()?.let {
                getMeals(it.name)
            }
        }
    }

    fun filterCategories(category: Category) {
        _categories.value = categories.value?.map {
            it.copy(selected = (category.name == it.name))
        }
        getMeals(category.name)
    }
}