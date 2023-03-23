package com.dawn.decisionapp.ui.screen.drink

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dawn.decisionapp.remote.DecisionApi
import com.dawn.decisionapp.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkViewModel @Inject constructor(
    private val navController: NavHostController,
    private val decisionApi: DecisionApi,
    private val snackbarService: SnackbarService,
): ViewModel(){

    private val _randomDrinkName = MutableStateFlow("")
    val randomDrinkName = _randomDrinkName.asStateFlow()

    private val _randomDrinkCategory = MutableStateFlow("")
    val randomDrinkCategory = _randomDrinkCategory.asStateFlow()

    private val _randomDrinkManufacturingCompany = MutableStateFlow("")
    val randomDrinkManufacturingCompany = _randomDrinkManufacturingCompany.asStateFlow()

    private val _randomDrinkImageUrl = MutableStateFlow("")
    val randomDrinkImageUrl = _randomDrinkImageUrl.asStateFlow()


    fun onEvent(event: DrinkUiEvent){
        when(event){
            DrinkUiEvent.CarbonatedDrinkButtonPressed -> {
                getRandomDrinkByCategory("탄산음료")
            }
            DrinkUiEvent.FruitDrinkButtonPressed -> {
                getRandomDrinkByCategory("과일음료")
            }
            DrinkUiEvent.CoffeeDrinkButtonPressed -> {
                getRandomDrinkByCategory("커피음료")
            }
            DrinkUiEvent.RetryButtonPressed -> {
                getRandomDrink()
            }
        }
    }

    private fun getRandomDrink(){
        viewModelScope.launch {
            val randomDrink = decisionApi.getRandomDrink().body()!!
            _randomDrinkName.value = randomDrink.name
            _randomDrinkCategory.value = randomDrink.category
            _randomDrinkManufacturingCompany.value = randomDrink.manufacturingCompany
            _randomDrinkImageUrl.value = randomDrink.image_url
        }
    }

    private fun getRandomDrinkByCategory(category: String){
        viewModelScope.launch{
            val randomDrink = decisionApi.getRandomDrinkByCategory(category).body()!!
            _randomDrinkName.value = randomDrink.name
            _randomDrinkCategory.value = randomDrink.category
            _randomDrinkManufacturingCompany.value = randomDrink.manufacturingCompany
            _randomDrinkImageUrl.value = randomDrink.image_url
        }
    }

    init {
        getRandomDrink()
    }
}

sealed class DrinkUiEvent{
    object CarbonatedDrinkButtonPressed : DrinkUiEvent()
    object FruitDrinkButtonPressed : DrinkUiEvent()
    object CoffeeDrinkButtonPressed : DrinkUiEvent()
    object RetryButtonPressed : DrinkUiEvent()
}