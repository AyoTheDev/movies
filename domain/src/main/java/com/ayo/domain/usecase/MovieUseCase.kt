package com.ayo.domain.usecase

import com.ayo.api.model.MovieApi
import com.ayo.data.repository.MovieDbRepository
import io.reactivex.Observable
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val movieDbRepository: MovieDbRepository) {
    fun getMovie(id: Int): Observable<MovieApi> = movieDbRepository.getMovieDetails(id)
}