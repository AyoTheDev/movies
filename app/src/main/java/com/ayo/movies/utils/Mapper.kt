package com.ayo.movies.utils

import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.ayo.domain.model.MovieDomain

//todo
fun MovieApi.toDomain(): MovieDomain {
    return MovieDomain(1)
}

fun Set<String>.toDomain(): List<MovieDomain> {
    return emptyList()
}

fun PopularMovieApi.toDomain(): List<MovieDomain> {
    return emptyList()
}

