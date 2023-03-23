package com.dawn.decisionapp.ui.screen.book

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import coil.compose.AsyncImage
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.section.DisplaySection
import com.dawn.decisionapp.ui.screen.DetailsScreen
import com.dawn.decisionapp.util.DetailsScreenRoute
import com.dawn.decisionapp.util.toKorean

val bookScreenRoute = DetailsScreenRoute.book_screen_route

fun NavGraphBuilder.bookScreen() {
    composable(route = bookScreenRoute.toKorean()) {
        BookScreen()
    }
}

fun NavController.navigateToBookScreen(navOptions: NavOptions? = null) {
    this.navigate(route = bookScreenRoute.toKorean(), navOptions = navOptions)
}

@Composable
fun BookScreen(
    viewModel: BookViewModel = hiltViewModel()
) {
    val randomBookTitle by viewModel.randomBookTitle.collectAsState()
    val randomBookAuthors by viewModel.randomBookAuthors.collectAsState()
    val randomBookImageUrl by viewModel.randomBookImageUrl.collectAsState()

    DetailsScreen {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            DisplaySection(headerText = "랜덤 도서 생성") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f),
                        model = randomBookImageUrl,
                        contentDescription = "Image"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "제목 : ")
                        Text(text = randomBookTitle)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "저자 : ")
                        Text(text = randomBookAuthors)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                StatefulButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { viewModel.onEvent(BookUiEvent.RetryButtonPressed) }
                ) {
                    Text(text = "다시 뽑기")
                }
            }
        }
    }
}