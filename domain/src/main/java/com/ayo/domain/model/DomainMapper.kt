package com.ayo.domain.model

import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.ayo.data.model.MovieData
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.lang.IllegalStateException

fun MovieDomain.toDataLayer(): MovieData {
    return MovieData(this.id, this.title, this.imgUrl, this.overview, this.runtime)
}

fun MovieApi.toDomain(): MovieDomain {
    return MovieDomain(this.id, this.title, "$IMG_URL_BASE${this.poster_path}", this.overview, this.runtime)
}

fun Set<String>.toDomain(gson: Gson): List<MovieDomain> {
    return try {
        this.map { gson.fromJson(it, MovieDomain::class.java) }
    }  catch (e: JsonSyntaxException){
        throw IllegalStateException("Cannot parse data")
    }
}

fun PopularMovieApi.toDomain(): List<MovieDomain> {
    return this.results.map {
        MovieDomain(it.id, it.title, "$IMG_URL_BASE${it.poster_path}", null, null) }
}

private const val IMG_URL_BASE = "https://image.tmdb.org/t/p/w500"

