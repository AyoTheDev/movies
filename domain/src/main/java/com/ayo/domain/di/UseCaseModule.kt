package com.ayo.domain.di

import com.ayo.domain.repository.MovieDbRepository
import com.ayo.domain.usecase.FavouriteMoviesUseCase
import com.ayo.domain.usecase.MovieUseCase
import com.ayo.domain.usecase.PopularMoviesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideMovieUseCase(moviesDbRepository: MovieDbRepository): MovieUseCase {
        return MovieUseCase(moviesDbRepository)
    }

    @Provides
    fun provideFavouriteMoviesUseCase(moviesDbRepository: MovieDbRepository): FavouriteMoviesUseCase {
        return FavouriteMoviesUseCase(moviesDbRepository)
    }

    @Provides
    fun providePopularMoviesUseCase(moviesDbRepository: MovieDbRepository): PopularMoviesUseCase {
        return PopularMoviesUseCase(moviesDbRepository)
    }
}