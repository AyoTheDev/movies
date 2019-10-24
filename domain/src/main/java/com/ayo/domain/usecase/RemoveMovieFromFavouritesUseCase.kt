package com.ayo.domain.usecase

import com.ayo.domain.model.MovieDomain
import com.ayo.domain.model.toDomain
import com.ayo.domain.repository.MovieDbRepository
import com.google.gson.Gson
import javax.inject.Inject

class RemoveMovieFromFavouritesUseCase
@Inject constructor(private val movieDbRepository: MovieDbRepository, private val gson: Gson) {

    fun removeMovie(id: Int): List<MovieDomain> = movieDbRepository.removeMovieFromFavourites(id).toDomain(gson)
}