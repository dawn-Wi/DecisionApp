package com.dawn.decisionapp.ui.screen.number

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.button.ButtonState
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.section.DisplaySection
import com.dawn.decisionapp.ui.screen.DetailsScreen
import com.dawn.decisionapp.util.DetailsScreenRoute
import com.dawn.decisionapp.util.toKorean

val numberScreenRoute = DetailsScreenRoute.number_screen_route

fun NavGraphBuilder.numberScreen() {
    composable(route = numberScreenRoute.toKorean()) {
        NumberScreen()
    }
}

fun NavController.navigateToNumberScreen(navOptions: NavOptions? = null) {
    this.navigate(route = numberScreenRoute.toKorean(), navOptions = navOptions)
}

@Composable
fun NumberScreen(
    viewModel: NumberViewModel = hiltViewModel()
) {
    val randomNumber by viewModel.randomNumber.collectAsState()
    val randomNumberLabel by viewModel.randomNumberLabel.collectAsState()

    val isVisibleSessionState by viewModel.visibleSessionState

    val startNumber by viewModel.startNumber.collectAsState()
    val endNumber by viewModel.endNumber.collectAsState()

    DetailsScreen {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            DisplaySection(headerText = "랜덤 수 생성") {
                Text(text = randomNumberLabel)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 70.sp,
                    text = randomNumber.toString()
                )
                Spacer(modifier = Modifier.height(20.dp))
                StatefulButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { viewModel.onEvent(NumberUiEvent.RetryButtonPressed) },
                    state =
                    if (!isVisibleSessionState) {
                        ButtonState.Enabled
                    } else {
                        ButtonState.Disabled
                    }
                ) {
                    Text(text = "다시뽑기")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StatefulButton(
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onEvent(NumberUiEvent.SingleNumberButtonPressed) },
                        state =
                        if (!isVisibleSessionState) {
                            ButtonState.Enabled
                        } else {
                            ButtonState.Disabled
                        }
                    ) {
                        Text(text = "0~9")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    StatefulButton(
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onEvent(NumberUiEvent.TwoNumberButtonPressed) },
                        state =
                        if (!isVisibleSessionState) {
                            ButtonState.Enabled
                        } else {
                            ButtonState.Disabled
                        }
                    ) {
                        Text(text = "0~99")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    StatefulButton(
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.onEvent(NumberUiEvent.VisibleSessionPressed) }
                    ) {
                        Text(text = "범위 지정")
                    }
                }
                SpecifyUserRange(
                    isVisible = isVisibleSessionState,
                    startNumber = startNumber,
                    endNumber = endNumber,
                    onUiEvent = { viewModel.onEvent(it) }
                )
            }
        }
    }
}

@Composable
fun SpecifyUserRange(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    startNumber: String,
    endNumber: String,
    onUiEvent: (NumberUiEvent) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(16.dp)
    ) {
        AnimatedVisibility(visible = isVisible) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = startNumber,
                        onValueChange = { onUiEvent(NumberUiEvent.OnStartNumberChanged(it)) },
                        label = { Text(text = "시작 숫자") }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = endNumber,
                        onValueChange = { onUiEvent(NumberUiEvent.OnEndNumberChanged(it)) },
                        label = { Text(text = "끝 숫자") }
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                StatefulButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { onUiEvent(NumberUiEvent.InputNumberButtonPressed) }
                ) {
                    Text(text = "숫자 뽑기")
                }
            }
        }
    }
}

