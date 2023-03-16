package com.dawn.decisionapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.dawn.decisionapp.ui.AppUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navController: NavHostController
): ViewModel(){
    fun onEvent(event: HomeUiEvent){
        when(event){
            HomeUiEvent.NumberButtonPressed -> TODO()
        }
    }
}

sealed class HomeUiEvent{
    object NumberButtonPressed: HomeUiEvent()
}