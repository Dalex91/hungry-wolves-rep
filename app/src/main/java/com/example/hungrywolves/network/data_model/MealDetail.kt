package com.example.hungrywolves.network.data_model

import com.squareup.moshi.Json

data class MealDetail(
    @Json(name = "strMeal") val name: String,
    @Json(name = "strInstructions") val instruction: String,
    @Json(name = "strTags") val tags : String?,
    @Json(name = "strMealThumb") val mealUrl : String,
    @Json(name = "strYoutube") val mealYoutubeUrl : String,
    @Json(name = "strIngredient1") val firstIngredient : String,
    @Json(name = "strIngredient2") val secondIngredient : String,
    @Json(name = "strIngredient3") val thirdIngredient : String,
    @Json(name = "strMeasure1") val firstMeasure : String,
    @Json(name = "strMeasure2") val secondMeasure : String,
    @Json(name = "strMeasure3") val thirdMeasure : String,
)
