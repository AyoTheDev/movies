package com.ayo.movies.di.module

import android.content.Context
import com.ayo.movies.App
import com.ayo.movies.common.CoroutineContextProvider
import com.ayo.data.SharedPrefs
import dagger.Module
import dagger.Provides

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


}