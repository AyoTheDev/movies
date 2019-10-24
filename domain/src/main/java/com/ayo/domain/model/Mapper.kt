package com.ayo.domain.model

import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.ayo.data.model.MovieData
import com.google.gson.Gson

fun MovieDomain.toDataLayer(): MovieData {
    return MovieData(this.id, this.title, this.imgUrl, this.overview, this.runtime)
}
fun MovieApi.toDomain(): MovieDomain {
    return MovieDomain(this.id, this.title, this.poster_path, this.overview, this.runtime)
}

fun Set<String>.toDomain(gson: Gson): List<MovieDomain> {
    return this.map { gson.fromJson(it, MovieDomain::class.java) }
}

fun PopularMovieApi.toDomain(): List<MovieDomain> {
    return this.results.map { MovieDomain(it.id, it.title, it.poster_path, null, null) }
}

fun String.prependMovieImageUrl(): String {
    return "https://image.tmdb.org/t/p/w500$this"
}
