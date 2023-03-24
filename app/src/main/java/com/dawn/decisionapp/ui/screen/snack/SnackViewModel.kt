package com.dawn.decisionapp.ui.screen.snack

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
class SnackViewModel @Inject constructor(
    private val navController: NavHostController,
    private val decisionApi: DecisionApi,
    private val snackbarService: SnackbarService
): ViewModel(){

    private val _randomSnackName = MutableStateFlow("")
    val randomSnackName = _randomSnackName.asStateFlow()

    private val _randomSnackManufacturingCompany = MutableStateFlow("")
    val randomSnackManufacturingCompany = _randomSnackManufacturingCompany.asStateFlow()

    private val _randomSnackImageUrl = MutableStateFlow("")
    val randomSnackImageUrl = _randomSnackImageUrl.asStateFlow()

    fun onEvent(event: SnackUiEvent){
        when(event){
            SnackUiEvent.RetryButtonPressed -> {
                getRandomSnack()
            }
        }
    }

    private fun getRandomSnack(){
        viewModelScope.launch {
            val randomSnack = decisionApi.getRandomSnack().body()!!
            _randomSnackName.value = randomSnack.name
            _randomSnackManufacturingCompany.value = randomSnack.manufacturingCompany
            _randomSnackImageUrl.value = randomSnack.image_url
        }
    }

    init {
        getRandomSnack()
    }
}
sealed class SnackUiEvent{
    object RetryButtonPressed : SnackUiEvent()
}