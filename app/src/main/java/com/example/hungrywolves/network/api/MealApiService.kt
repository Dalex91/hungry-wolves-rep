package com.example.hungrywolves.network

import com.example.hungrywolves.network.data_model.DataMealsSource
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

private val retrofitByCategory = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

private val retrofitByName = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

private val retrofitById = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MealApiByCategoryService {
    @GET("filter.php")
    suspend fun getMeals(@Query("c") category: String) : DataMealsSource
}

interface MealApiByNameService {
    @GET("search.php")
    suspend fun getMeals(@Query("s") name: String) : DataMealsSource
}

interface MealApiByIdService {
    @GET("lookup.php")
    suspend fun getMeals(@Query("i") name: String) : DataMealsSource
}

object MealsApi {
    val retrofitServiceByCategory : MealApiByCategoryService by lazy {
        retrofitByCategory.create(MealApiByCategoryService::class.java)
    }

    val retrofitServiceByName : MealApiByNameService by lazy {
        retrofitByName.create(MealApiByNameService::class.java)
    }

    val retrofitServiceById : MealApiByIdService by lazy {
        retrofitByName.create(MealApiByIdService::class.java)
    }
}
