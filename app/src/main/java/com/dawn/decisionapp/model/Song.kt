package com.dawn.decisionapp.model

import java.util.Date

data class Song(
    val id: Long,
    val title: String,
    val singer: String,
    val releaseDate: Date,
    val category: String
)
