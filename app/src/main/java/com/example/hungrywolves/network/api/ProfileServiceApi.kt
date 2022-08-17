package com.example.hungrywolves.network.api

import co.infinum.retromock.Retromock
import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockBehavior
import co.infinum.retromock.meta.MockResponse
import com.example.hungrywolves.network.data_model.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofitService = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

private var retromock = Retromock.Builder()
    .retrofit(retrofitService)
    .build()

interface ProfileApiService {
    @Mock
    @MockBehavior(durationMillis = 400, durationDeviation = 100)
    @MockResponse(body = "{\"name\":\"Bogdan-Alexandru Dig\", \"phoneNumber\" : \"+40 123 456 789\", " +
            "\"email\" : \"bogdandig91@gmail.com\"}")
    @GET("/")
    suspend fun getUserProfile() : User
}

object ProfileApi {
    val retrofitService : ProfileApiService by lazy {
        retromock.create(ProfileApiService::class.java)
    }
}