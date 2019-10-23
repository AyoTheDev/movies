package com.ayo.domain.usecase

import com.ayo.domain.model.MovieDomain
import com.ayo.domain.repository.MovieDbRepository
import javax.inject.Inject

class FavouriteMoviesUseCase @Inject constructor(private val movieDbRepository: MovieDbRepository) {

    fun getFavouriteMovies() = movieDbRepository.getFavouriteMovies()

    fun addMovie(movie: MovieDomain) = movieDbRepository.addMovieToFavourites(movie)

    fun removeMovie(id: Int) = movieDbRepository.removeMovieFromFavourites(id)
}