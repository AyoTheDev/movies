package com.ayo.api.services

import com.ayo.api.endpoints.MovieDbEndpoints
import javax.inject.Inject

class MovieDbService @Inject constructor(private val endpoints: MovieDbEndpoints) {

    suspend fun getMovie(id: Int) = endpoints.getMovie(id).body()

    suspend fun getPopularMovies() = endpoints.getPopularMovies().body()
}