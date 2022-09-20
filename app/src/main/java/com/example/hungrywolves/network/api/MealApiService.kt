package com.example.hungrywolves.network

import com.example.hungrywolves.models.DataDetailMealSource
import com.example.hungrywolves.models.DataMealsSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

private val retrofitService = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MealApiService {
    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String) : DataMealsSource

    @GET("search.php")
    suspend fun getMealsByName(@Query("s") name: String) : DataMealsSource

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id : String) : DataDetailMealSource
}


object MealsApi {
    val retrofitServiceMeal : MealApiService by lazy {
        retrofitService.create(MealApiService::class.java)
    }
}
