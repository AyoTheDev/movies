package com.ayo.movies.di.module

import android.content.Context
import com.ayo.api.utils.SchedulerProvider
import com.ayo.movies.App
import com.ayo.movies.common.CoroutineContextProvider
import com.ayo.data.SharedPrefs
import com.ayo.movies.utils.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

@Module
class ApplicationModule(private val app: App){

    @Provides
    fun provideContext(): Context = app.applicationContext

    @Provides
    fun provideCoroutineContext(): CoroutineContextProvider {
        return CoroutineContextProvider()
    }

    @Provides
    fun provideSharedPrefs(context: Context): SharedPrefs {
        return SharedPrefs(context)
    }

    @Provides
    internal fun provideSchedulerProvider() : SchedulerProvider = AppSchedulerProvider()

    @Provides
    internal fun provideCompositeDisposable() : CompositeDisposable = CompositeDisposable()
}