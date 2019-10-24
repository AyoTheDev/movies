package com.ayo.movies

import com.ayo.api.di.NetworkModule
import com.ayo.domain.di.UseCaseModule
import com.ayo.movies.di.component.DaggerApplicationComponent
import com.ayo.movies.di.module.ApplicationModule
import com.ayo.movies.di.module.RepositoryModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .repositoryModule(RepositoryModule())
            .useCaseModule(UseCaseModule())
            .build()
    }
}