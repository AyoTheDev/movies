package com.ayo.api.endpoints

import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbEndpoints {

    @GET("3/movie/{id}")
    fun getMovie(@Path("id") id: Int): Observable<MovieApi>

    @GET("4/discover/movie")
    fun getPopularMovies(@Query("sort_by") sortBy: String = "popularity.desc"): Observable<PopularMovieApi>
}