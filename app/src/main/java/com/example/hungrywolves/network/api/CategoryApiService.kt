package com.example.hungrywolves.network

import com.example.hungrywolves.models.DataCategoriesSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL= "https://www.themealdb.com/api/json/v1/1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CategoryApiService {
    @GET("categories.php")
    suspend fun getCategories() : DataCategoriesSource
}

object CategoryApi {
    val retrofitService : CategoryApiService by lazy {
        retrofit.create(CategoryApiService::class.java)
    }
}