package com.ayo.api.services

import com.ayo.api.endpoints.MovieDbEndpoints
import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MovieDbService @Inject constructor(private val endpoints: MovieDbEndpoints) : MovieDbEndpoints {

    /*fun getMovie(id: Int) = endpoints.getMovie(id).body()

    suspend fun getPopularMovies() = endpoints.getPopularMovies().body()*/
    override fun getMovie(id: Int): Observable<MovieApi> {
        return endpoints.getMovie(id)
    }

    override fun getPopularMovies(sortBy: String): Observable<PopularMovieApi> {
        return endpoints.getPopularMovies()
    }
}