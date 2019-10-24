package com.ayo.data

import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.ayo.api.services.MovieDbService
import com.ayo.domain.model.MovieDomain
import com.ayo.domain.model.toDomain
import com.ayo.domain.repository.MovieDbRepository
import com.google.gson.Gson
import javax.inject.Inject

class MovieDbRepositoryImpl @Inject constructor(
    private val service: MovieDbService,
    private val sharedPrefs: SharedPrefs,
    private val gson: Gson
) : MovieDbRepository {

    override fun addMovieToFavourites(movie: MovieDomain): Set<String> {
        val set = sharedPrefs.favourites?.toMutableSet() ?: mutableSetOf()
        val movieJson = gson.toJson(movie)
        set.add(movieJson)
        sharedPrefs.favourites = set
        return set
    }

    override fun removeMovieFromFavourites(id: Int): Set<String> {
        val oldSet = sharedPrefs.favourites?.toMutableSet() ?: return emptySet()
        val movies = oldSet.toDomain(gson).toMutableList().filter { it.id != id }
        val newSet = movies.map { gson.toJson(it) }.toSet()
        sharedPrefs.favourites = newSet
        return newSet
    }

    override suspend fun getMovieDetails(id: Int): MovieApi? {
        return service.getMovie(id)
    }

    override fun getFavouriteMovies(): Set<String>? {
        return sharedPrefs.favourites
    }

    override suspend fun getPopularMovies(): PopularMovieApi? {
        return service.getPopularMovies()
    }
}