package com.ayo.movies.utils

import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.ayo.domain.model.MovieDomain

fun MovieApi.toDomain(): MovieDomain {
    return MovieDomain(1, "", "")
}

fun Set<String>.toDomain(): List<MovieDomain> {
    return emptyList()
}

fun PopularMovieApi.toDomain(): List<MovieDomain> {
    return this.results.map { MovieDomain(id = it.id, title = it.title, imgUrl = it.poster_path ) }
}

