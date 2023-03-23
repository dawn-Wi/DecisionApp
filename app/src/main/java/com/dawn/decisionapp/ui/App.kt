package com.dawn.decisionapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.dawn.decisionapp.ui.screen.book.bookScreen
import com.dawn.decisionapp.ui.screen.color.colorScreen
import com.dawn.decisionapp.ui.screen.drama.dramaScreen
import com.dawn.decisionapp.ui.screen.drink.drinkScreen
import com.dawn.decisionapp.ui.screen.food.foodScreen
import com.dawn.decisionapp.ui.screen.home.homeScreen
import com.dawn.decisionapp.ui.screen.home.homeScreenRoute
import com.dawn.decisionapp.ui.screen.movie.movieScreen
import com.dawn.decisionapp.ui.screen.number.numberScreen
import com.dawn.decisionapp.ui.screen.song.songScreen

@Composable
fun App(
    appViewModel: AppViewModel = hiltViewModel()
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            //TopBar
        },
        bottomBar = {
            //bottomBar
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            NavHost(navController = appViewModel.navController, startDestination = homeScreenRoute ){
                homeScreen()
                numberScreen()
                colorScreen()
                foodScreen()
                songScreen()
                dramaScreen()
                movieScreen()
                drinkScreen()
                bookScreen()
            }
        }
    }
}