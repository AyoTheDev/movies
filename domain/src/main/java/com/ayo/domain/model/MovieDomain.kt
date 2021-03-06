package com.ayo.domain.model

data class MovieDomain(
    val id: Int,
    val title: String,
    val imgUrl: String,
    val overview: String?,
    val runtime: Int?
)