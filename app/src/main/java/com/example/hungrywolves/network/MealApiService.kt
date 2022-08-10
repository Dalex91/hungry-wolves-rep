package com.example.hungrywolves.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MealApiService {
    @GET("filter.php")
    suspend fun getMeals(@Query("c") category: String = "Beef") : DataMealsSource
}

object MealsApi {
    val retrofitService : MealApiService by lazy {
        retrofit.create(MealApiService::class.java)
    }
}