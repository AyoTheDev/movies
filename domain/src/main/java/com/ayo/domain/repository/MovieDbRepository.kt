package com.ayo.domain.repository

import com.ayo.domain.model.MovieDomain

interface MovieDbRepository {

    suspend fun getMovie(id: Int): MovieDomain

    suspend fun getFavouriteMovies(): List<MovieDomain>

    suspend fun getPopularMovies(): List<MovieDomain>
}