package com.ayo.domain.usecase

import com.ayo.domain.repository.MovieDbRepository
import javax.inject.Inject

class FavouriteMoviesUseCase @Inject constructor(private val movieDbRepository: MovieDbRepository) {

    suspend fun getFavouriteMovies() = movieDbRepository.getFavouriteMovies()
}