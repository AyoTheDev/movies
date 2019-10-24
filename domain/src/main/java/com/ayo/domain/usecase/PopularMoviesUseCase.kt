package com.ayo.domain.usecase

import com.ayo.domain.model.MovieDomain
import com.ayo.domain.model.toDomain
import com.ayo.domain.repository.MovieDbRepository
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(private val movieDbRepository: MovieDbRepository) {

    suspend fun getPopularMovies(): List<MovieDomain>? = movieDbRepository.getPopularMovies()?.toDomain()
}