package com.ayo.movies.data

import com.ayo.api.services.MovieDbService
import com.ayo.domain.model.MovieDomain
import com.ayo.domain.repository.MovieDbRepository
import com.ayo.movies.utils.toDomain
import com.google.gson.Gson
import javax.inject.Inject

class MovieDbRepositoryImpl @Inject constructor(
    private val service: MovieDbService,
    private val sharedPrefs: SharedPrefs,
    private val gson: Gson
) : MovieDbRepository {

    override fun addMovieToFavourites(movie: MovieDomain): List<MovieDomain> {
        val set = sharedPrefs.favourites?.toMutableSet() ?: mutableSetOf()
        val movieJson = gson.toJson(movie)
        set.add(movieJson)
        sharedPrefs.favourites = set
        return set.toDomain(gson)
    }

    override fun removeMovieFromFavourites(id: Int): List<MovieDomain> {
        val oldSet = sharedPrefs.favourites?.toMutableSet() ?: return emptyList()
        val movies = oldSet.toDomain(gson).toMutableList().filter { it.id != id }
        val newSet = movies.map { gson.toJson(it) }.toSet()
        sharedPrefs.favourites = newSet
        return newSet.toDomain(gson)
    }

    override suspend fun getMovieDetails(id: Int): MovieDomain? {
        return service.getMovie(id)?.toDomain()
    }

    override fun getFavouriteMovies(): List<MovieDomain>? {
        return sharedPrefs.favourites?.toDomain(gson) ?: emptyList()
    }

    override suspend fun getPopularMovies(): List<MovieDomain>? {
        return service.getPopularMovies()?.toDomain()
    }
}