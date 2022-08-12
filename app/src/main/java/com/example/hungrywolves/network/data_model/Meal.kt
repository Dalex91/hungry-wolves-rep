package com.example.hungrywolves.network.data_model

import com.squareup.moshi.Json

data class Meal(
    val idMeal: Int,
    @Json(name = "strMeal") val name: String,
    @Json(name = "strMealThumb") val mealUrl : String,
)