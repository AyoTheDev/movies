package com.ayo.api.model

data class PopularMovieApi(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)