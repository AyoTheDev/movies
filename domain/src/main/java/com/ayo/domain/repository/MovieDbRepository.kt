package com.ayo.domain.repository

import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.ayo.domain.model.MovieDomain

interface MovieDbRepository {

    suspend fun getMovieDetails(id: Int): MovieApi?

    fun getFavouriteMovies():  Set<String>?

    suspend fun getPopularMovies(): PopularMovieApi?

    fun addMovieToFavourites(movie: MovieDomain): Set<String>

    fun removeMovieFromFavourites(id: Int): Set<String>

}