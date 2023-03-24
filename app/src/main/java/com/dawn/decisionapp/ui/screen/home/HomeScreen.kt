package com.dawn.decisionapp.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Audiotrack
import androidx.compose.material.icons.twotone.Book
import androidx.compose.material.icons.twotone.BreakfastDining
import androidx.compose.material.icons.twotone.Contrast
import androidx.compose.material.icons.twotone.Fastfood
import androidx.compose.material.icons.twotone.LiveTv
import androidx.compose.material.icons.twotone.LocalDrink
import androidx.compose.material.icons.twotone.Movie
import androidx.compose.material.icons.twotone.Pin
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.baec23.ludwig.component.section.DisplaySection

const val homeScreenRoute = "home_screen_route"

fun NavGraphBuilder.homeScreen() {
    composable(route = homeScreenRoute) {
        HomeScreen()
    }
}

fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) {
    this.navigate(route = homeScreenRoute, navOptions = navOptions)
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DisplaySection(
            headerText = "카테고리 선택"
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PressIconButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .border(
                            width = 3.dp,
                            color = Color.Red,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = { viewModel.onEvent(HomeUiEvent.NumberButtonPressed) },
                    icon = {
                        AnimatedVisibility(visible = true) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.TwoTone.Pin,
                                contentDescription = "NUMBER",
                            )
                        }
                    },
                    text = {
                        Text(
                            fontSize = 25.sp,
                            text = "숫자",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
                PressIconButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .border(
                            width = 3.dp,
                            color = Color.Yellow,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = { viewModel.onEvent(HomeUiEvent.ColorButtonPressed) },
                    icon = {
                        AnimatedVisibility(visible = true) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.TwoTone.Contrast,
                                contentDescription = "COLOR",
                            )
                        }
                    },
                    text = {
                        Text(
                            fontSize = 25.sp,
                            text = "색깔",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
                PressIconButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .border(
                            width = 3.dp,
                            color = Color.Green,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = { viewModel.onEvent(HomeUiEvent.BookButtonPressed) },
                    icon = {
                        AnimatedVisibility(visible = true) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.TwoTone.Book,
                                contentDescription = "BOOK",
                            )
                        }
                    },
                    text = {
                        Text(
                            fontSize = 23.sp,
                            text = "도서",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PressIconButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .border(
                            width = 3.dp,
                            color = Color.Cyan,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = { viewModel.onEvent(HomeUiEvent.SongButtonPressed) },
                    icon = {
                        AnimatedVisibility(visible = true) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.TwoTone.Audiotrack,
                                contentDescription = "SONG",
                            )
                        }
                    },
                    text = {
                        Text(
                            fontSize = 25.sp,
                            text = "노래",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
                PressIconButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .border(
                            width = 3.dp,
                            color = Color.Blue,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = { viewModel.onEvent(HomeUiEvent.DramaButtonPressed) },
                    icon = {
                        AnimatedVisibility(visible = true) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.TwoTone.LiveTv,
                                contentDescription = "DRAMA",
                            )
                        }
                    },
                    text = {
                        Text(
                            fontSize = 23.sp,
                            text = "드라마",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
                PressIconButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .border(
                            width = 3.dp,
                            color = Color.Magenta,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = { viewModel.onEvent(HomeUiEvent.MovieButtonPressed) },
                    icon = {
                        AnimatedVisibility(visible = true) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.TwoTone.Movie,
                                contentDescription = "MOVIE",
                            )
                        }
                    },
                    text = {
                        Text(
                            fontSize = 25.sp,
                            text = "영화",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PressIconButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .border(
                            width = 3.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = { viewModel.onEvent(HomeUiEvent.DrinkButtonPressed) },
                    icon = {
                        AnimatedVisibility(visible = true) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.TwoTone.LocalDrink,
                                contentDescription = "DRINK",
                            )
                        }
                    },
                    text = {
                        Text(
                            fontSize = 25.sp,
                            text = "음료",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
                PressIconButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .border(
                            width = 3.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = { viewModel.onEvent(HomeUiEvent.FoodButtonPressed) },
                    icon = {
                        AnimatedVisibility(visible = true) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.TwoTone.Fastfood,
                                contentDescription = "FOOD",
                            )
                        }
                    },
                    text = {
                        Text(
                            fontSize = 25.sp,
                            text = "음식",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
                PressIconButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .border(
                            width = 3.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = { viewModel.onEvent(HomeUiEvent.SnackButtonPressed) },
                    icon = {
                        AnimatedVisibility(visible = true) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                imageVector = Icons.TwoTone.BreakfastDining,
                                contentDescription = "SNACK",
                            )
                        }
                    },
                    text = {
                        Text(
                            fontSize = 25.sp,
                            text = "과자",
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun PressIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() },
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        colors = colors,
        interactionSource = interactionSource
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(
                visible = isPressed,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                if (isPressed) {
                    icon()
                }
            }
            text()
        }
    }
}