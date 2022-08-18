package com.example.hungrywolves.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hungrywolves.InitApp.Companion.USERNAME
import com.example.hungrywolves.network.MealsApi
import com.example.hungrywolves.network.data_model.MealDetail
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.launch

const val COMMA = ","

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
        _tags.value = _mealDetail.value?.tags?.split(COMMA)
    }

    fun checkFav(isSelected: Boolean) {
        var favMap : MutableMap<String, MealDetail?>? = Hawk.get(USERNAME)
        favMap = favMap?.let {
            when(Pair(isAdded(), isSelected)) {
                Pair(false, true) ->  it.put(_idMeal, _mealDetail.value)
                Pair(true, false) -> it.remove(_idMeal)
                else -> {}
            }
            it
        } ?: run {
            HashMap()
        }
        Hawk.put(USERNAME, favMap)
    }

    fun isAdded() : Boolean{
        val favMap : MutableMap<String, MealDetail?>? = Hawk.get(USERNAME)
        return favMap?.containsKey(_idMeal) ?: false
    }
}