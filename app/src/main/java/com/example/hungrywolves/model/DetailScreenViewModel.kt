package com.example.hungrywolves.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolves.network.MealsApi
import com.example.hungrywolves.network.data_model.MealDetail
import kotlinx.coroutines.launch

class DetailScreenViewModel : ViewModel(){

    private val _mealDetail = MutableLiveData<MealDetail?>()
    val mealDetail : LiveData<MealDetail?> = _mealDetail

    private val _tags = MutableLiveData<List<String>?>()
    val tags : LiveData<List<String>?> = _tags

    fun getMealDetails(id: String) {
        viewModelScope.launch {
            try {
                _mealDetail.value = MealsApi.retrofitServiceMeal.getMealById(id).meals.firstOrNull()
                extractTags()
            }
            catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun extractTags() {
        _tags.value = _mealDetail.value?.tags?.split(",")
    }
}