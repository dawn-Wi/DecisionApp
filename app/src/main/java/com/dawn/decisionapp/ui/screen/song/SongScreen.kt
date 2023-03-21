package com.dawn.decisionapp.ui.screen.song

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.section.DisplaySection
import com.dawn.decisionapp.ui.screen.DetailsScreen
import com.dawn.decisionapp.util.DetailsScreenRoute
import com.dawn.decisionapp.util.toKorean

val songScreenRoute = DetailsScreenRoute.song_screen_route

fun NavGraphBuilder.songScreen() {
    composable(route = songScreenRoute.toKorean()) {
        SongScreen()
    }
}

fun NavController.navigateToSongScreen(navOptions: NavOptions? = null) {
    this.navigate(route = songScreenRoute.toKorean(), navOptions = navOptions)
}

@Composable
fun SongScreen(
    viewModel: SongViewModel = hiltViewModel()
) {
    DetailsScreen {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            DisplaySection(headerText = "랜덤 노래 생성") {

            }
        }
    }
}