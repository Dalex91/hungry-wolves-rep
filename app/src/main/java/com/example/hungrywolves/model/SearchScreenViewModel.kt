package com.example.hungrywolves.model

import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.transition.Visibility
import com.example.hungrywolves.network.data_model.Meal
import com.example.hungrywolves.network.MealsApi
import kotlinx.coroutines.*

const val DELAY_TIME : Long = 500

class SearchScreenViewModel : ViewModel() {
    private val _meals = MutableLiveData<List<Meal>?>()
    val meals: LiveData<List<Meal>?> = _meals

    private val _mealName = MutableLiveData("")
    val mealName: LiveData<String?> = _mealName

    private val _numberOfResults = MutableLiveData(0)
    val numberOfResults: LiveData<Int> = _numberOfResults

    private val _textVisibility = MutableLiveData<Boolean>(false)
    val textVisibility: LiveData<Boolean> = _textVisibility

    private var job: Job = Job()

    init {
        getSearchedMeals()
    }

    fun getSearchedMeals() {
        job.cancel()
        job = viewModelScope.launch {
            delay(DELAY_TIME)
            try {
                _meals.value = MealsApi.retrofitServiceByName.getMealsByName(_mealName.value ?: "")
                    .meals
                _numberOfResults.value = _meals.value?.size ?: 0
                setVisibility((_mealName.value != ""))
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

    fun setVisibility(visibility: Boolean) {
        _textVisibility.value = visibility
    }
}