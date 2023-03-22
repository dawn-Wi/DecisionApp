package com.dawn.decisionapp.ui.screen.song

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.dawn.decisionapp.remote.DecisionApi
import com.dawn.decisionapp.repository.DecisionRepository
import com.dawn.decisionapp.service.SnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val navController : NavHostController,
    private val decisionApi: DecisionApi,
    private val snackbarService: SnackbarService,
): ViewModel(){

    private val _randomSongTitle = MutableStateFlow("")
    val randomSongTitle = _randomSongTitle.asStateFlow()

    private val _randomSongSinger = MutableStateFlow("")
    val randomSongSinger = _randomSongSinger.asStateFlow()

    private val _randomSongReleaseDate = MutableStateFlow("")
    val randomSongReleaseDate = _randomSongReleaseDate.asStateFlow()

    private val _randomSongCategory = MutableStateFlow("")
    val randomSongCategory = _randomSongCategory.asStateFlow()

    private val dateFormat = "yyyy-MM-dd"
    @SuppressLint("SimpleDateFormat")
    private val simpleDateFormat = SimpleDateFormat(dateFormat)

    fun onEvent(event : SongUiEvent){
        when(event){
            SongUiEvent.DanceButtonPressed -> {
                getRandomSongByCategory("댄스")
            }
            SongUiEvent.BalladButtonPressed -> {
                getRandomSongByCategory("발라드")
            }
            SongUiEvent.OtherButtonPressed -> {
                getRandomSongByCategoryIsOther()
            }
            SongUiEvent.RetryButtonPressed -> {
                getRandomSong()
            }
        }
    }

    private fun getRandomSong(){
        viewModelScope.launch {
            val randomSong = decisionApi.getRandomSong().body()!!
            _randomSongTitle.value = randomSong.title
            _randomSongSinger.value = randomSong.singer
            val simpleDate: String = simpleDateFormat.format(randomSong.releaseDate)
            _randomSongReleaseDate.value = simpleDate
            _randomSongCategory.value = randomSong.category
        }
    }

    private fun getRandomSongByCategory(category: String){
        viewModelScope.launch {
            val randomSong = decisionApi.getRandomSongByCategory(category).body()!!
            _randomSongTitle.value = randomSong.title
            _randomSongSinger.value = randomSong.singer
            val simpleDate: String = simpleDateFormat.format(randomSong.releaseDate)
            _randomSongReleaseDate.value = simpleDate
            _randomSongCategory.value = randomSong.category
        }
    }

    private fun getRandomSongByCategoryIsOther(){
        viewModelScope.launch{
            val randomSong = decisionApi.getRandomSongByCategoryIsOther().body()!!
            _randomSongTitle.value = randomSong.title
            _randomSongSinger.value = randomSong.singer
            val simpleDate: String = simpleDateFormat.format(randomSong.releaseDate)
            _randomSongReleaseDate.value = simpleDate
            _randomSongCategory.value = randomSong.category
        }
    }

    init {
        getRandomSong()
    }
}

sealed class SongUiEvent{
    object DanceButtonPressed : SongUiEvent()
    object BalladButtonPressed: SongUiEvent()
    object OtherButtonPressed : SongUiEvent()
    object RetryButtonPressed : SongUiEvent()
}
