package com.dawn.decisionapp.ui.screen.color

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(
    private val navController: NavHostController
): ViewModel(){
    fun onEvent(event: ColorUiEvent){

    }
}

sealed class ColorUiEvent{

}