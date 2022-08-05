package com.ayo.domain.usecase

import com.ayo.api.model.PopularMovieApi
import com.ayo.data.repository.MovieDbRepository
import io.reactivex.Observable
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(private val movieDbRepository: MovieDbRepository) {
    fun getPopularMovies(): Observable<PopularMovieApi> = movieDbRepository.getPopularMovies()
}