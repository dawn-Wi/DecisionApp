package com.dawn.decisionapp.util

enum class DetailsScreenRoute{
    number_screen_route,
    color_screen_route,
    food_screen_route,
    song_screen_route,
    drama_screen_route,
    movie_screen_route,
    drink_screen_route,
    book_screen_route,
    snack_screen_route,
}

fun DetailsScreenRoute.toKorean():String{
    return when(this){
        DetailsScreenRoute.number_screen_route -> "숫자"
        DetailsScreenRoute.color_screen_route -> "색깔"
        DetailsScreenRoute.food_screen_route -> "음식"
        DetailsScreenRoute.song_screen_route -> "노래"
        DetailsScreenRoute.drama_screen_route -> "드라마"
        DetailsScreenRoute.movie_screen_route -> "영화"
        DetailsScreenRoute.drink_screen_route -> "음료"
        DetailsScreenRoute.book_screen_route -> "도서"
        DetailsScreenRoute.snack_screen_route -> "과자"
    }
}