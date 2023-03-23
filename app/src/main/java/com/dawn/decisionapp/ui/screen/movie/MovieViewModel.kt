package com.dawn.decisionapp.ui.screen.movie

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
class MovieViewModel @Inject constructor(
    private val navController: NavHostController,
    private val decisionApi: DecisionApi,
    private val snackbarService: SnackbarService,
): ViewModel(){

    private val _randomMovieTitle = MutableStateFlow("")
    val randomMovieTitle = _randomMovieTitle.asStateFlow()

    private val _randomMovieImageUrl = MutableStateFlow("")
    val randomMovieImageUrl = _randomMovieImageUrl.asStateFlow()

    private val _randomMovieCategory = MutableStateFlow("")
    val randomMovieCategory = _randomMovieCategory.asStateFlow()

    private val _randomMovieActor = MutableStateFlow("")
    val randomMovieActor = _randomMovieActor.asStateFlow()

    fun onEvent(event: MovieUiEvent){
        when(event){
            MovieUiEvent.RetryButtonPressed -> {
                getRandomMovie()
            }
        }
    }

    private fun getRandomMovie(){
        viewModelScope.launch {
            val randomMovie = decisionApi.getRandomMovie().body()!!
            _randomMovieTitle.value = randomMovie.title
            _randomMovieCategory.value = randomMovie.category
            _randomMovieImageUrl.value = randomMovie.image_url
            _randomMovieActor.value = randomMovie.actor
        }
    }

    init {
        getRandomMovie()
    }
}

sealed class MovieUiEvent{
    object RetryButtonPressed: MovieUiEvent()
}