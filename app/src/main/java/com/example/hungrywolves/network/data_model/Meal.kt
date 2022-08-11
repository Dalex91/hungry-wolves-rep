package com.example.hungrywolves.network.data_model

import com.squareup.moshi.Json

open class Meal(
    open val idMeal: Int,
    @Json(name = "strMeal") open val name: String,
    @Json(name = "strMealThumb") open val mealUrl : String
)