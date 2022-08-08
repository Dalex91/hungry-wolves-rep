package com.example.hungrywolves.network

import com.squareup.moshi.Json

data class Category(
    val idCategory : Int,
    @Transient var selected : Boolean = false,
    @Json(name = "strCategory") val name: String,
    @Json(name = "strCategoryThumb") val categoryUrl : String,
    @Json(name = "strCategoryDescription") val description : String)