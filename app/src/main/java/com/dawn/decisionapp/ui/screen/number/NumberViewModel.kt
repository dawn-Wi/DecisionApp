package com.dawn.decisionapp.ui.screen.number

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.dawn.decisionapp.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NumberViewModel @Inject constructor(
    private val navController: NavHostController,
    private val snackbarService: SnackbarService,
): ViewModel(){

    private val _randomNumber = MutableStateFlow(0)
    val randomNumber = _randomNumber.asStateFlow()

    private val _randomNumberLabel = MutableStateFlow("한 자리 숫자")
    val randomNumberLabel = _randomNumberLabel.asStateFlow()

    val visibleSessionState: MutableState<Boolean> = mutableStateOf(false)

    private val _startNumber = MutableStateFlow("0")
    val startNumber = _startNumber.asStateFlow()

    private val _endNumber = MutableStateFlow("99")
    val endNumber = _endNumber.asStateFlow()

    fun onEvent(event: NumberUiEvent){
        when(event){
            NumberUiEvent.RetryButtonPressed -> {
                getRandomNumber()
            }
            NumberUiEvent.SingleNumberButtonPressed -> {
                getSingleRandomNumber()
            }
            NumberUiEvent.TwoNumberButtonPressed -> {
                getTwoRandomNumber()
            }

            is NumberUiEvent.OnStartNumberChanged -> {
                _startNumber.value = event.startNumber
            }

            is NumberUiEvent.OnEndNumberChanged ->{
                _endNumber.value = event.endNumber
            }

            NumberUiEvent.VisibleSessionPressed -> {
                visibleSessionState.value = !visibleSessionState.value
            }

            NumberUiEvent.InputNumberButtonPressed -> {
                if (numberValid(_startNumber.value, _endNumber.value)){
                    getInputRandomNumber(_startNumber.value,_endNumber.value)
                }else{
                    snackbarService.showSnackbar("숫자 범위 입력이 잘못되었습니다.")
                }
            }
        }
    }

    private fun getRandomNumber() {
        val range = (0..999)
        _randomNumber.value = range.random()
        _randomNumberLabel.value = "0 ~ 999"
    }

    private fun getSingleRandomNumber(){
        val range = (0..9)
        _randomNumber.value = range.random()
        _randomNumberLabel.value = "0 ~ 9"
    }

    private fun getTwoRandomNumber(){
        val range = (0..99)
        _randomNumber.value = range.random()
        _randomNumberLabel.value = "0 ~ 99"
    }
    private fun getInputRandomNumber(startNumber: String, endNumber: String){
        val range = (startNumber.toInt()..endNumber.toInt())
        _randomNumber.value = range.random()
        _randomNumberLabel.value = "0 ~ 99"
    }

    private fun numberValid(startNumber: String, endNumber: String): Boolean {
        return startNumber.toInt() <= endNumber.toInt()
    }

    init {
        getRandomNumber()
    }
}

sealed class NumberUiEvent{
    data class OnStartNumberChanged(val startNumber: String) : NumberUiEvent()
    data class OnEndNumberChanged(val endNumber: String): NumberUiEvent()
    object RetryButtonPressed : NumberUiEvent()
    object SingleNumberButtonPressed : NumberUiEvent()
    object TwoNumberButtonPressed : NumberUiEvent()
    object VisibleSessionPressed : NumberUiEvent()
    object InputNumberButtonPressed: NumberUiEvent()
}