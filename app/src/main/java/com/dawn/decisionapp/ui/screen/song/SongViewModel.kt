package com.dawn.decisionapp.ui.screen.song

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.dawn.decisionapp.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val navController : NavHostController,
    private val snackbarService: SnackbarService,
): ViewModel(){

    fun onEvent(event : SongUiEvent){
        when(event){

            else -> {}
        }
    }
}

sealed class SongUiEvent{

}
