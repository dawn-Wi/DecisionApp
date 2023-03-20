package com.dawn.decisionapp.remote

import com.dawn.decisionapp.model.Food
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DecisionApi {
    @GET("food")
    suspend fun getFoodList(): Response<List<Food>>

    @GET("food/category")
    suspend fun getFoodListByCategory(@Query("category")category: String) :Response<List<Food>>
}