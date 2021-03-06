package com.ayo.movies.di.component

import android.app.Application
import com.ayo.api.di.NetworkModule
import com.ayo.movies.App
import com.ayo.movies.di.builder.ActivityBuilder
import com.ayo.movies.di.builder.FragmentBuilder
import com.ayo.movies.di.module.ApplicationModule
import com.ayo.movies.di.module.RepositoryModule
import com.ayo.movies.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(app: App)
}