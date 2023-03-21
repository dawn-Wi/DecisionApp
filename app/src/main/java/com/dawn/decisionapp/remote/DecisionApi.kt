package com.dawn.decisionapp.remote

import com.dawn.decisionapp.model.Food
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DecisionApi {

    @GET("num")
    suspend fun getRandomNumber(): Response<Int>

    @GET("num/single")
    suspend fun getSingleRandomNumber(): Response<Int>

    @GET("num/two")
    suspend fun getTwoRandomNumber(): Response<Int>

    @GET("num/input")
    suspend fun getInputRandomNumber(
        @Query("startNumber") startNumber: String,
        @Query("endNumber") endNumber: String
    ): Response<Int>

    @GET("color")
    suspend fun getRandomColor(): Response<List<Int>>

    @GET("food")
    suspend fun getFoodList(): Response<List<Food>>

    @GET("food/category")
    suspend fun getFoodListByCategory(@Query("category") category: String): Response<List<Food>>
}