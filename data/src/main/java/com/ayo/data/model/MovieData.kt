package com.ayo.data.model

data class MovieData(
    val id: Int,
    val title: String,
    val imgUrl: String,
    val overview: String?,
    val runtime: Int?
)