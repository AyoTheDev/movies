package com.ayo.movies.di.module

import android.content.Context
import com.ayo.movies.common.CoroutineContextProvider
import com.ayo.movies.data.SharedPrefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context){

    @Singleton
    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideCoroutineContext(): CoroutineContextProvider {
        return CoroutineContextProvider()
    }

    @Provides
    fun provideSharedPrefs(context: Context): SharedPrefs {
        return SharedPrefs(context)
    }


}