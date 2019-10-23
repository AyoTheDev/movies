package com.ayo.movies.utils

import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.ayo.domain.model.MovieDomain

fun MovieApi.toDomain(): MovieDomain {
    return MovieDomain(this.id, this.title, this.poster_path, this.overview, this.runtime.toString())
}

fun Set<String>.toDomain(): List<MovieDomain> {
    return emptyList()
}

fun PopularMovieApi.toDomain(): List<MovieDomain> {
    return this.results.map { MovieDomain(it.id, it.title, it.poster_path, null, null) }
}

