package com.ayo.domain.usecase

import com.ayo.domain.repository.MovieDbRepository
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(private val movieDbRepository: MovieDbRepository) {

    suspend fun getFavouriteMovies() = movieDbRepository.getPopularMovies()
}