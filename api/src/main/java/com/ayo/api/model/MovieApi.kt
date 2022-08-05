package com.ayo.api.model

data class MovieApi(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview: String,
    val runtime: Int
)