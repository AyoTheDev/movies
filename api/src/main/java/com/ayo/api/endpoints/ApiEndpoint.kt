package com.ayo.api.endpoints

class ApiEndpoint {
    val BASE_URL = "https://api.themoviedb.org/"
    val getPopularMovies : String
        get() = BASE_URL + "4/discover/movie"
}