package com.example.hungrywolves.models

import com.squareup.moshi.Json

data class Meal(
    val idMeal: Int,
    @Json(name = "strMeal") val name: String,
    @Json(name = "strMealThumb") val mealUrl : String,
)