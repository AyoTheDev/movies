package com.ayo.domain.usecase

import com.ayo.domain.model.MovieDomain
import com.ayo.domain.model.toDomain
import com.ayo.domain.repository.MovieDbRepository
import com.google.gson.Gson
import javax.inject.Inject

class AddMovieToFavouritesUseCase
@Inject constructor(private val movieDbRepository: MovieDbRepository,  private val gson: Gson) {

    fun addMovie(movie: MovieDomain): List<MovieDomain> = movieDbRepository.addMovieToFavourites(movie).toDomain(gson)

}