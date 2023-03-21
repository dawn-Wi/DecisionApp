package com.dawn.decisionapp.repository

import android.util.Log
import com.dawn.decisionapp.model.Song
import com.dawn.decisionapp.remote.DecisionApi
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DecisionRepository @Inject constructor(
    private val decisionApi: DecisionApi
) {
    private val _foodList = ArrayList<String>()
    private val _song = ArrayList<Song>()

    suspend fun getFoodList(): List<String>{
        _foodList.clear()
        val response = decisionApi.getFoodList()
        if (response.isSuccessful){
            response.body()!!.forEach {
                _foodList.add(it.name)
            }
        }
        return _foodList
    }

    suspend fun getFoodListByCategory(category: String) : List<String>{
        _foodList.clear()
        val response = decisionApi.getFoodListByCategory(category)
        if (response.isSuccessful){
            response.body()!!.forEach {
                _foodList.add(it.name)
                Log.d("getFoodListByCategory", "getFoodListByCategory: $_foodList")
            }
        }
        return _foodList
    }
}