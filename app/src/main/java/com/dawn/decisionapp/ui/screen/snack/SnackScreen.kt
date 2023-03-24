package com.dawn.decisionapp.ui.screen.snack

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

val snackScreenRoute = DetailsScreenRoute.snack_screen_route

fun NavGraphBuilder.snackScreen(){
    composable(route = snackScreenRoute.toKorean()){
        SnackScreen()
    }
}

fun NavController.navigateToSnackScreen(navOptions: NavOptions? = null){
    this.navigate(route = snackScreenRoute.toKorean(), navOptions = navOptions)
}

@Composable
fun SnackScreen(
    viewModel: SnackViewModel = hiltViewModel()
){
    val randomSnackName by viewModel.randomSnackName.collectAsState()
    val randomSnackManufacturingCompany by viewModel.randomSnackManufacturingCompany.collectAsState()
    val randomSnackImageUrl by viewModel.randomSnackImageUrl.collectAsState()

    DetailsScreen {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        )  {
            DisplaySection(headerText = "랜덤 과자 생성") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f),
                        model = randomSnackImageUrl,
                        contentDescription = "Image"
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "과자이름 : ")
                        Text(text = randomSnackName)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "제조사 : ")
                        Text(text = randomSnackManufacturingCompany)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Spacer(modifier = Modifier.height(20.dp))
                StatefulButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { viewModel.onEvent(SnackUiEvent.RetryButtonPressed) }
                ) {
                    Text(text = "다시 뽑기")
                }
            }
        }
    }
}