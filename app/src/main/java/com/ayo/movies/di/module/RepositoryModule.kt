package com.ayo.movies.di.module

import com.ayo.api.services.MovieDbService
import com.ayo.data.repository.MovieDbRepository
import com.ayo.data.SharedPrefs
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(service: MovieDbService, sharedPrefs: SharedPrefs, gson: Gson): MovieDbRepository {
        return MovieDbRepository(service, sharedPrefs, gson)
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}