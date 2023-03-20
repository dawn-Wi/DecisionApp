package com.dawn.decisionapp.ui.screen.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
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

val colorScreenRoute = DetailsScreenRoute.color_screen_route

fun NavGraphBuilder.colorScreen(){
    composable(route = colorScreenRoute.toKorean()){
        ColorScreen()
    }
}

fun NavController.navigateToColorScreen(navOptions: NavOptions? = null){
    this.navigate(route = colorScreenRoute.toKorean(), navOptions = navOptions)
}

@Composable
fun ColorScreen(
    viewModel: ColorViewModel = hiltViewModel()
){
    val randomColorR by viewModel.randomColorR.collectAsState()
    val randomColorG by viewModel.randomColorG.collectAsState()
    val randomColorB by viewModel.randomColorB.collectAsState()

    val colorInt by viewModel.colorInt.collectAsState()

    val hexToColorInt = String.format("#%06X", 0xFFFFFF and colorInt)
    
    DetailsScreen {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            DisplaySection(headerText = "랜덤 색 생성") {
                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .background(color = Color(colorInt))
                        .align(Alignment.CenterHorizontally)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "hex : $hexToColorInt") //HEX
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "rgb ( ${randomColorR}, ${randomColorG}, $randomColorB )") //RGB
                    Spacer(modifier = Modifier.height(10.dp))
                }
                StatefulButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { viewModel.onEvent(ColorUiEvent.RetryButtonPressed)}
                ) {
                    Text(text = "다시 뽑기")
                }
            }
        }
    }
}