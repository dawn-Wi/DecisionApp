package com.dawn.decisionapp.ui.screen.food

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
import androidx.compose.ui.unit.sp
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

val foodScreenRoute = DetailsScreenRoute.food_screen_route

fun NavGraphBuilder.foodScreen() {
    composable(route = foodScreenRoute.toKorean()) {
        FoodScreen()
    }
}

fun NavController.navigateToFoodScreen(navOptions: NavOptions? = null) {
    this.navigate(route = foodScreenRoute.toKorean(), navOptions = navOptions)
}

@Composable
fun FoodScreen(
    viewModel: FoodViewModel = hiltViewModel()
) {
    val food by viewModel.food.collectAsState()

    DetailsScreen {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            DisplaySection(
                headerText = "랜덤 음식 생성"
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier,
                        text = food,
                        fontSize = 50.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    StatefulButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { viewModel.onEvent(FoodUiEvent.KoreanFoodButtonPressed) }
                    ) {
                        Text(text = "한식")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    StatefulButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { viewModel.onEvent(FoodUiEvent.WesternFoodButtonPressed) }
                    ) {
                        Text(text = "양식")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    StatefulButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { viewModel.onEvent(FoodUiEvent.ChineseFoodButtonPressed) }
                    ) {
                        Text(text = "중식")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    StatefulButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { viewModel.onEvent(FoodUiEvent.JapaneseFoodButtonPressed) }
                    ) {
                        Text(text = "일식")
                    }
                }
                StatefulButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { viewModel.onEvent(FoodUiEvent.RetryButtonPressed) }
                ) {
                    Text(text = "다시 뽑기")
                }
            }
        }
    }
}