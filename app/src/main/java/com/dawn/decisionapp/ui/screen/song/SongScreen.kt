package com.dawn.decisionapp.ui.screen.song

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.button.StatefulButton
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
    val randomSongTitle by viewModel.randomSongTitle.collectAsState()
    val randomSongSinger by viewModel.randomSongSinger.collectAsState()
    val randomSongReleaseDate by viewModel.randomSongReleaseDate.collectAsState()
    val randomSongCategory by viewModel.randomSongCategory.collectAsState()

    DetailsScreen {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            DisplaySection(headerText = "랜덤 노래 생성") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                    ){
                        Text(text = "제목 : ")
                        Text(text = randomSongTitle)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ){
                        Text(text = "가수 : ")
                        Text(text = randomSongSinger)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "발매일 : ")
                        Text(text = "$randomSongReleaseDate")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "장르 : ")
                        Text(text = randomSongCategory)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Row {
                    StatefulButton(
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onEvent(SongUiEvent.DanceButtonPressed)}
                    ) {
                        Text(text = "댄스")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    StatefulButton(
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onEvent(SongUiEvent.BalladButtonPressed)}
                    ) {
                        Text(text = "발라드")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    StatefulButton(
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onEvent(SongUiEvent.OtherButtonPressed)}
                    ) {
                        Text(text = "기타")
                    }
                }
                StatefulButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { viewModel.onEvent(SongUiEvent.RetryButtonPressed)}
                ) {
                    Text(text = "다시 뽑기")
                }
            }
        }
    }
}