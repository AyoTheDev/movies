package com.ayo.movies.di.builder

import com.ayo.movies.ui.PopularMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentBuilder {

    @ContributesAndroidInjector
    fun contributePopularMoviesFragment(): PopularMoviesFragment
}
