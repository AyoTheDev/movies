package com.ayo.data.repository

import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.ayo.api.services.MovieDbService
import com.ayo.data.SharedPrefs
import com.ayo.data.model.MovieData
import com.ayo.data.model.toDomain
import com.google.gson.Gson
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class MovieDbRepository @Inject constructor(
    private val service: MovieDbService,
    private val sharedPrefs: SharedPrefs,
    private val gson: Gson
) {

    fun addMovieToFavourites(movie: MovieData): Set<String> {
        val set = sharedPrefs.favourites?.toMutableSet() ?: mutableSetOf()
        val movieJson = gson.toJson(movie)
        set.add(movieJson)
        sharedPrefs.favourites = set
        return set
    }

    fun removeMovieFromFavourites(id: Int): Set<String> {
        val oldSet = sharedPrefs.favourites?.toMutableSet() ?: return emptySet()
        val movies = oldSet.toDomain(gson).toMutableList().filter { it.id != id }
        val newSet = movies.map { gson.toJson(it) }.toSet()
        sharedPrefs.favourites = newSet
        return newSet
    }

    fun getMovieDetails(id: Int): Observable<MovieApi> {
        return service.getMovie(id)
    }

    fun getFavouriteMovies(): Set<String>? {
        return sharedPrefs.favourites
    }

    fun getPopularMovies(): Observable<PopularMovieApi> {
        return service.getPopularMovies()
    }
}