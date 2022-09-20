package com.example.hungrywolves.ui.search

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolves.models.Meal
import com.example.hungrywolves.network.MealsApi
import kotlinx.coroutines.*

const val DELAY_TIME : Long = 500
const val BLANK_TEXT : String = ""

class SearchScreenViewModel : ViewModel() {
    private val _meals = MutableLiveData<List<Meal>?>()
    val meals: LiveData<List<Meal>?> = _meals

    private val _mealName = MutableLiveData("")
    val mealName: LiveData<String?> = _mealName

    private val _numberOfResults = MutableLiveData(0)
    val numberOfResults: LiveData<Int> = _numberOfResults

    private val _textVisibility = MutableLiveData(false)
    val textVisibility: LiveData<Boolean> = _textVisibility

    private val _placeholderVisibility = MutableLiveData(false)
    val placeholderVisibility : LiveData<Boolean> = _placeholderVisibility

    private var job: Job = Job()

    init {
        getSearchedMeals()
    }

    fun getSearchedMeals() {
        job.cancel()
        job = viewModelScope.launch {
            delay(DELAY_TIME)
            try {
                _meals.value = MealsApi.retrofitServiceMeal.getMealsByName(_mealName.value ?: "").meals
                updateNumberOfResult(_meals.value?.size ?: 0)
                setPlaceHolderVisibility((numberOfResults.value == 0) && (_mealName.value != ""))
                setVisibility((_mealName.value != "") && (placeholderVisibility.value == false))
            } catch (e: Exception) {
                Log.d("mes", "${e.message}")
            }
        }
    }

    private fun setPlaceHolderVisibility(value : Boolean) {
        _placeholderVisibility.value = value
    }

    fun updateNumberOfResult(nr : Int) {
        _numberOfResults.value = nr
    }

    fun updateMealName(s: Editable) {
        _mealName.value = s.toString()
    }

    fun cleanText() {
        _mealName.value = BLANK_TEXT
    }

    private fun setVisibility(visibility: Boolean) {
        _textVisibility.value = visibility
    }
}