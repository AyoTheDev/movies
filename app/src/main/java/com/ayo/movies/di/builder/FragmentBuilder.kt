package com.ayo.movies.di.builder

import com.ayo.movies.ui.movies.fragment.MovieDetailsDialogFragment
import com.ayo.movies.ui.movies.fragment.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector
    fun contributePopularMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    fun contributeMovieDetailsDialogFragment(): MovieDetailsDialogFragment
}
