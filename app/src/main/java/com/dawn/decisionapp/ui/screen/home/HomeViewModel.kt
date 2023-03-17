package com.dawn.decisionapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.dawn.decisionapp.ui.AppUiEvent
import com.dawn.decisionapp.ui.screen.color.navigateToColorScreen
import com.dawn.decisionapp.ui.screen.food.navigateToFoodScreen
import com.dawn.decisionapp.ui.screen.number.navigateToNumberScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navController: NavHostController
) : ViewModel() {


    fun onEvent(event: HomeUiEvent) {
        when (event) {
            HomeUiEvent.NumberButtonPressed -> {
                navController.navigateToNumberScreen()
            }

            HomeUiEvent.ColorButtonPressed -> {
                navController.navigateToColorScreen()
            }

            HomeUiEvent.FoodButtonPressed -> {
                navController.navigateToFoodScreen()
            }
        }
    }
}


sealed class HomeUiEvent {
    object NumberButtonPressed : HomeUiEvent()
    object ColorButtonPressed : HomeUiEvent()
    object FoodButtonPressed : HomeUiEvent()
}