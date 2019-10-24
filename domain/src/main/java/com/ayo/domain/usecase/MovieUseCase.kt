package com.ayo.domain.usecase

import com.ayo.domain.model.MovieDomain
import com.ayo.domain.model.toDomain
import com.ayo.domain.repository.MovieDbRepository
import com.google.gson.Gson
import javax.inject.Inject

class MovieUseCase
@Inject constructor(private val movieDbRepository: MovieDbRepository) {

    suspend fun getMovie(id: Int): MovieDomain? = movieDbRepository.getMovieDetails(id)?.toDomain()
}