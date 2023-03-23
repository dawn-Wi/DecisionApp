package com.dawn.decisionapp.ui.screen.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dawn.decisionapp.remote.DecisionApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val decisionApi: DecisionApi,
    private val navController: NavHostController
) : ViewModel(){

    private val _randomFoodName = MutableStateFlow<String>("")
    val randomFoodName = _randomFoodName.asStateFlow()

    private val _randomFoodCategory = MutableStateFlow<String>("")
    val randomFoodCategory = _randomFoodCategory.asStateFlow()

    private val _randomFoodImageUrl = MutableStateFlow<String>("a")
    val randomFoodImageUrl = _randomFoodImageUrl.asStateFlow()

    fun onEvent(event: FoodUiEvent){
        when(event){
            FoodUiEvent.RetryButtonPressed -> {
                getRandomFood()
            }

            FoodUiEvent.WesternFoodButtonPressed -> {
                getRandomFoodByCategory("양식")
            }
            FoodUiEvent.KoreanFoodButtonPressed ->{
                getRandomFoodByCategory("한식")
            }
            FoodUiEvent.ChineseFoodButtonPressed ->{
                getRandomFoodByCategory("중식")
            }
            FoodUiEvent.JapaneseFoodButtonPressed -> {
                getRandomFoodByCategory("일식")
            }
        }
    }

    private fun getRandomFood(){
        viewModelScope.launch {
            val randomFood = decisionApi.getRandomFood().body()!!
            _randomFoodName.value = randomFood.name
            _randomFoodCategory.value = randomFood.category
            _randomFoodImageUrl.value = randomFood.image_url
        }
    }
    private fun getRandomFoodByCategory(category: String){
        viewModelScope.launch {
            val randomFood = decisionApi.getRandomFoodByCategory(category).body()!!
            _randomFoodName.value = randomFood.name
            _randomFoodCategory.value = randomFood.category
            _randomFoodImageUrl.value = randomFood.image_url
        }
    }

    init {
        getRandomFood()
    }
}
sealed class FoodUiEvent{
    object WesternFoodButtonPressed : FoodUiEvent()
    object KoreanFoodButtonPressed : FoodUiEvent()
    object ChineseFoodButtonPressed : FoodUiEvent()
    object JapaneseFoodButtonPressed: FoodUiEvent()
    object RetryButtonPressed : FoodUiEvent()
}