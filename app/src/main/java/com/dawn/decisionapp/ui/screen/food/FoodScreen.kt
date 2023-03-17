package com.dawn.decisionapp.ui.screen.food

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.dawn.decisionapp.util.DetailsScreenRoute
import com.dawn.decisionapp.util.toKorean

val foodScreenRoute = DetailsScreenRoute.food_screen_route

fun NavGraphBuilder.foodScreen(){
    composable(route = foodScreenRoute.toKorean()){
        FoodScreen()
    }
}

fun NavController.navigateToFoodScreen(navOptions: NavOptions? = null){
    this.navigate(route = foodScreenRoute.toKorean(), navOptions = navOptions)
}

@Composable
fun FoodScreen(
    viewModel: FoodViewModel = hiltViewModel()
){

}