package com.ayo.api.services

import com.ayo.api.di.NetworkModule
import com.ayo.api.endpoints.ApiEndpoint
import com.ayo.api.endpoints.MovieDbEndpoints
import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import retrofit2.Response
import javax.inject.Inject

class MovieDbService @Inject constructor(private val endpoints: MovieDbEndpoints) : MovieDbEndpoints {

    /*fun getMovie(id: Int) = endpoints.getMovie(id).body()

    suspend fun getPopularMovies() = endpoints.getPopularMovies().body()*/

    companion object {
        private const val TIME_OUT = 1L
        private const val BASE_URL = "https://api.themoviedb.org/"
        private const val API_KEY_FIELD = "api_key"
        private const val API_KEY = "d3b018581c65b4ac18d55a61391e87ac"
    }

    override fun getMovie(id: Int): Observable<MovieApi> {
        return endpoints.getMovie(id)
    }

    override fun getPopularMovies(sortBy: String): Observable<PopularMovieApi> {
        val temp =  Rx2AndroidNetworking.get(ApiEndpoint().getPopularMovies)
            .addQueryParameter(API_KEY_FIELD, API_KEY)
            .build()
            .getObjectObservable(PopularMovieApi::class.java)

        return temp
    }
}