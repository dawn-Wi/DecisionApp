package com.dawn.decisionapp.ui.screen.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dawn.decisionapp.remote.DecisionApi
import com.dawn.decisionapp.repository.DecisionRepository
import com.dawn.decisionapp.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val decisionRepository: DecisionRepository,
    private val navController: NavHostController
) : ViewModel(){

    private val _food = MutableStateFlow<String>("a")
    val food = _food.asStateFlow()
    fun onEvent(event: FoodUiEvent){
        when(event){
            FoodUiEvent.RetryButtonPressed -> {
                getFoodList()
            }

            FoodUiEvent.WesternFoodButtonPressed -> {
                getFoodListByCategory("양식")
            }
            FoodUiEvent.KoreanFoodButtonPressed ->{
                getFoodListByCategory("한식")
            }
            FoodUiEvent.ChineseFoodButtonPressed ->{
                getFoodListByCategory("중식")
            }
            FoodUiEvent.JapaneseFoodButtonPressed -> {
                getFoodListByCategory("일식")
            }
        }
    }

    private fun getFoodList(){
        viewModelScope.launch {
            _food.value = decisionRepository.getFoodList().random()
        }
    }
    private fun getFoodListByCategory(category: String){
        viewModelScope.launch {
            _food.value = decisionRepository.getFoodListByCategory(category).random()
        }
    }

    init {
        getFoodList()
    }
}
sealed class FoodUiEvent{
    object WesternFoodButtonPressed : FoodUiEvent()
    object KoreanFoodButtonPressed : FoodUiEvent()
    object ChineseFoodButtonPressed : FoodUiEvent()
    object JapaneseFoodButtonPressed: FoodUiEvent()
    object RetryButtonPressed : FoodUiEvent()
}