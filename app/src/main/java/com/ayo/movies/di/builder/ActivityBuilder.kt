package com.ayo.movies.di.builder

import com.ayo.movies.ui.breakingbad.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {
    @ContributesAndroidInjector
    fun contributeActivity(): MainActivity
}
