package com.dawn.decisionapp.ui.screen.food

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val navController: NavHostController
) : ViewModel(){
    fun onEvent(event: FoodUiEvent){
        when(event){

            else -> {}
        }
    }
}
sealed class FoodUiEvent{

}