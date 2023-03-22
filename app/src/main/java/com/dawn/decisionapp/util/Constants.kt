package com.dawn.decisionapp.util

enum class DetailsScreenRoute{
    number_screen_route,
    color_screen_route,
    food_screen_route,
    song_screen_route,
    drama_screen_route,
}

fun DetailsScreenRoute.toKorean():String{
    return when(this){
        DetailsScreenRoute.number_screen_route -> "숫자"
        DetailsScreenRoute.color_screen_route -> "색깔"
        DetailsScreenRoute.food_screen_route -> "음식"
        DetailsScreenRoute.song_screen_route -> "노래"
        DetailsScreenRoute.drama_screen_route -> "드라마"
    }
}