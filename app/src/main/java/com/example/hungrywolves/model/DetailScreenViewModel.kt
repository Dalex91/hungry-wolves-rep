package com.example.hungrywolves.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolves.network.MealsApi
import com.example.hungrywolves.network.data_model.MealDetail
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.launch

class DetailScreenViewModel : ViewModel(){

    private lateinit var _idMeal : String
    private val _mealDetail = MutableLiveData<MealDetail?>()
    val mealDetail : LiveData<MealDetail?> = _mealDetail

    private val _tags = MutableLiveData<List<String>?>()
    val tags : LiveData<List<String>?> = _tags

    fun getMealDetails(id: String) {
        _idMeal = id
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

    fun checkFav(isSelected: Boolean) {
        if (isSelected) {
            if (!Hawk.contains(_idMeal))
                Hawk.put(_idMeal, _mealDetail.value)
        }
        else {
            if (Hawk.contains(_idMeal))
                Hawk.delete(_idMeal)
        }
    }
}