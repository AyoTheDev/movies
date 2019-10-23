package com.ayo.domain.repository

import com.ayo.domain.model.MovieDomain

interface MovieDbRepository {

    suspend fun getMovie(id: Int): MovieDomain?

    fun getFavouriteMovies(): List<MovieDomain>?

    suspend fun getPopularMovies(): List<MovieDomain>?

    fun addMovieToFavourites(movie: MovieDomain): List<MovieDomain>

    fun removeMovieFromFavourites(id: Int): List<MovieDomain>

}