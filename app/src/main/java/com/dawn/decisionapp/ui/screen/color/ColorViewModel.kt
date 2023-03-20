package com.dawn.decisionapp.ui.screen.color

import android.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.dawn.decisionapp.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(
    private val navController: NavHostController,
    private val snackbarService: SnackbarService,
): ViewModel(){


    private val _randomColorR = MutableStateFlow(0)
    val randomColorR = _randomColorR.asStateFlow()

    private val _randomColorG = MutableStateFlow(0)
    val randomColorG = _randomColorG.asStateFlow()

    private val _randomColorB = MutableStateFlow(0)
    val randomColorB = _randomColorB.asStateFlow()

    private val _colorInt = MutableStateFlow(0)
    val colorInt = _colorInt.asStateFlow()

    fun onEvent(event: ColorUiEvent){
        when(event){
            ColorUiEvent.RetryButtonPressed -> {
                getRandomColor()
            }
        }
    }

    private fun getRandomColor(){
        val range = (0..255)
        _randomColorR.value = range.random()
        _randomColorG.value = range.random()
        _randomColorB.value = range.random()
        _colorInt.value = Color.argb(255, randomColorR.value, randomColorG.value, randomColorB.value)
    }

    init {
        getRandomColor()
    }
}

sealed class ColorUiEvent{
    object RetryButtonPressed : ColorUiEvent()
}