package com.ayo.api.di

import com.ayo.api.endpoints.MovieDbEndpoints
import com.ayo.api.services.MovieDbService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {

    @Provides
    fun provideMovieDbService(movieDbEndpoints: MovieDbEndpoints) = MovieDbService(movieDbEndpoints)
}