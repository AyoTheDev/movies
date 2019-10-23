package com.ayo.movies.di.builder

import com.ayo.movies.ui.FavouriteMoviesFragment
import com.ayo.movies.ui.MovieDetailsDialogFragment
import com.ayo.movies.ui.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector
    fun contributePopularMoviesFragment(): PopularMoviesFragment

    @ContributesAndroidInjector
    fun contributesFavouriteMoviesFragment(): FavouriteMoviesFragment

    @ContributesAndroidInjector
    fun contributeMovieDetailsDialogFragment(): MovieDetailsDialogFragment
}
