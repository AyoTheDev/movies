package com.ayo.api.services

import com.ayo.api.endpoints.MovieDbEndpoints
import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.function.Consumer

import javax.inject.Inject

class MovieDbService @Inject constructor(private val endpoints: MovieDbEndpoints)  {

    fun getMovie(id: Int): Observable<MovieApi> {
        return endpoints.getMovie(id)
    }

    fun getPopularMovies() : Observable<PopularMovieApi> {
        return endpoints.getPopularMovies()
    }
}