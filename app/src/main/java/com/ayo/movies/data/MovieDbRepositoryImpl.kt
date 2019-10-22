package com.ayo.movies.data

import com.ayo.api.services.MovieDbService
import com.ayo.domain.model.MovieDomain
import com.ayo.domain.repository.MovieDbRepository
import com.ayo.movies.utils.toDomain
import javax.inject.Inject

class MovieDbRepositoryImpl @Inject constructor(
    private val service: MovieDbService,
    private val sharedPrefs: SharedPrefs
): MovieDbRepository {

    override suspend fun getMovie(id: Int): MovieDomain? {
        return service.getMovie(id)?.toDomain()
    }

    override suspend fun getFavouriteMovies(): List<MovieDomain>? {
        return sharedPrefs.favourites?.toDomain()
    }

    override suspend fun getPopularMovies(): List<MovieDomain>? {
        return service.getPopularMovies()?.toDomain()
    }
}