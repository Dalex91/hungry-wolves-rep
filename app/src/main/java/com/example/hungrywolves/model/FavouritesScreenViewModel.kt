package com.example.hungrywolves.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hungrywolves.InitApp.Companion.USERNAME
import com.example.hungrywolves.network.data_model.MealDetail
import com.orhanobut.hawk.Hawk

class FavouritesScreenViewModel : ViewModel() {

    private val _favourites = MutableLiveData<List<MealDetail>?>()
    val favourites : LiveData<List<MealDetail>?> = _favourites

    fun getFavourites() {
        val favMap : MutableMap<String, MealDetail>? = Hawk.get(USERNAME)
        setFavourites(favMap)
    }

    private fun setFavourites(favMap : MutableMap<String, MealDetail>?) {
        _favourites.value = favMap?.values?.toList()
    }

    fun deleteItem(id : String) {
        val favMap : MutableMap<String, MealDetail>? = Hawk.get(USERNAME)
        favMap?.remove(id)
        Hawk.put(USERNAME, favMap)
        setFavourites(favMap)
    }
}