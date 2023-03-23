package com.dawn.decisionapp.ui.screen.drink

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
import coil.compose.AsyncImage
import com.baec23.ludwig.component.button.StatefulButton
import com.baec23.ludwig.component.section.DisplaySection
import com.dawn.decisionapp.ui.screen.DetailsScreen
import com.dawn.decisionapp.ui.screen.food.FoodUiEvent
import com.dawn.decisionapp.util.DetailsScreenRoute
import com.dawn.decisionapp.util.toKorean

val drinkScreenRoute = DetailsScreenRoute.drink_screen_route

fun NavGraphBuilder.drinkScreen(){
    composable(route = drinkScreenRoute.toKorean()){
        DrinkScreen()
    }
}

fun NavController.navigateToDrinkScreen(navOptions: NavOptions? = null){
    this.navigate(route = drinkScreenRoute.toKorean(), navOptions = navOptions)
}

@Composable
fun DrinkScreen(
    viewModel: DrinkViewModel = hiltViewModel()
){
    val randomDrinkName by viewModel.randomDrinkName.collectAsState()
    val randomDrinkCategory by viewModel.randomDrinkCategory.collectAsState()
    val randomDrinkManufacturingCompany by viewModel.randomDrinkManufacturingCompany.collectAsState()
    val randomDrinkImageUrl by viewModel.randomDrinkImageUrl.collectAsState()

    DetailsScreen {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            DisplaySection(headerText = "랜덤 음료 생성") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Start),
                        text = "< $randomDrinkCategory >",
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f),
                        model = randomDrinkImageUrl,
                        contentDescription = "Image"
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "음료이름 : ")
                        Text(text = randomDrinkName)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "제조사 : ")
                        Text(text = randomDrinkManufacturingCompany)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    StatefulButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { viewModel.onEvent(DrinkUiEvent.CarbonatedDrinkButtonPressed) }
                    ) {
                        Text(text = "탄산음료")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    StatefulButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { viewModel.onEvent(DrinkUiEvent.FruitDrinkButtonPressed) }
                    ) {
                        Text(text = "과일음료")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    StatefulButton(
                        modifier = Modifier
                            .weight(1f),
                        onClick = { viewModel.onEvent(DrinkUiEvent.CoffeeDrinkButtonPressed) }
                    ) {
                        Text(text = "커피음료")
                    }
                }
                StatefulButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { viewModel.onEvent(DrinkUiEvent.RetryButtonPressed) }
                ) {
                    Text(text = "다시 뽑기")
                }
            }
        }
    }
}