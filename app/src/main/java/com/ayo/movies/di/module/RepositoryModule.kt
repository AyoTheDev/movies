package com.ayo.movies.di.module

import com.ayo.api.services.MovieDbService
import com.ayo.domain.repository.MovieDbRepository
import com.ayo.movies.data.MovieDbRepositoryImpl
import com.ayo.movies.data.SharedPrefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(service: MovieDbService, sharedPrefs: SharedPrefs): MovieDbRepository {
        return MovieDbRepositoryImpl(service, sharedPrefs)
    }
}