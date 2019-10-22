package com.ayo.api.endpoints

import com.ayo.api.model.MovieApi
import com.ayo.api.model.PopularMovieApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbEndpoints {

    @GET("3/movie/{id}")
    suspend fun getMovie(@Path("id") id: Int): Response<MovieApi>

    @GET("4/discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(): Response<PopularMovieApi>

}