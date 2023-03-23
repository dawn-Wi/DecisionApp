package com.dawn.decisionapp.remote

import com.dawn.decisionapp.model.Drama
import com.dawn.decisionapp.model.Drink
import com.dawn.decisionapp.model.Food
import com.dawn.decisionapp.model.Movie
import com.dawn.decisionapp.model.Song
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
    suspend fun getRandomFood(): Response<Food>

    @GET("food/category")
    suspend fun getRandomFoodByCategory(@Query("category") category: String): Response<Food>

    @GET("song")
    suspend fun getRandomSong(): Response<Song>

    @GET("song/category")
    suspend fun getRandomSongByCategory(@Query("category") category: String): Response<Song>

    @GET("song/category/other")
    suspend fun getRandomSongByCategoryIsOther(): Response<Song>

    @GET("drama")
    suspend fun getRandomDrama(): Response<Drama>

    @GET("movie")
    suspend fun getRandomMovie(): Response<Movie>

    @GET("drink")
    suspend fun getRandomDrink(): Response<Drink>

    @GET("drink/category")
    suspend fun getRandomDrinkByCategory(@Query("category")category: String): Response<Drink>
}