package com.example.hungrywolves.model

import android.text.Editable
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolves.network.Meal
import com.example.hungrywolves.network.MealsApi
import kotlinx.coroutines.*

class SearchScreenViewModel : ViewModel() {
    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> = _meals

    private val _mealName = MutableLiveData("")
    val mealName: LiveData<String?> = _mealName

    private val _numberOfResults = MutableLiveData(0)
    val numberOfResults: LiveData<Int> = _numberOfResults

    private val _textVisibility = MutableLiveData(View.INVISIBLE)
    val textVisibility: LiveData<Int> = _textVisibility

    private var job: Job = Job()

    init {
        getSearchedMeals()
    }

    fun getSearchedMeals() {
        job.cancel()
        job = viewModelScope.launch {
            delay(500)
            try {
                mealName.value?.let {
                    _meals.value = MealsApi.retrofitServiceByName.getMeals(it).meals
                } ?: run {
                    _meals.value = MealsApi.retrofitServiceByName.getMeals("").meals
                }
                _numberOfResults.value = _meals.value?.size ?: 0
                _textVisibility.value = if (mealName.value == "") TextView.INVISIBLE else
                    TextView.VISIBLE
            } catch (e: Exception) {
                Log.d("mes", "${e.message}")
            }
        }
    }

    fun updateMealName(s: Editable) {
        _mealName.value = s.toString()
    }

    fun cleanText() {
        _mealName.value = ""
    }

}