package com.dawn.decisionapp.ui.screen.home

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.section.DisplaySection

const val homeScreenRoute = "home_screen_route"

fun NavGraphBuilder.homeScreen(){
    composable(route = homeScreenRoute){
        HomeScreen()
    }
}

fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null){
    this.navigate(route = homeScreenRoute, navOptions = navOptions)
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
){
    Surface (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        DisplaySection(headerText = "카테고리 선택") {
            Row (
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.LightGray),
                    onClick = {viewModel.onEvent(HomeUiEvent.NumberButtonPressed)}
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier.weight(1f),
                            imageVector = Icons.Default.Check,
                            contentDescription = "NUMBER"
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            color = Color.Black,
                            fontSize = 20.sp,
                            text = "숫자"
                        )
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {viewModel.onEvent(HomeUiEvent.NumberButtonPressed)}
                ) {
                    Text(text = "색")
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {viewModel.onEvent(HomeUiEvent.NumberButtonPressed)}
                ) {
                    Text(text = "음식")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}