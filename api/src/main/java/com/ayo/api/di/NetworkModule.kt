package com.ayo.api.di

import android.content.Context
import com.ayo.api.endpoints.EndPoints
import com.ayo.api.interceptors.NetworkConnectivityInterceptor
import com.ayo.api.interceptors.NetworkResponseInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    companion object {
        private const val TIME_OUT = 1L
        private const val BASE_URL = "https://api.stackexchange.com/2.2/"
    }

    @Provides
    fun provideEndpoints(retrofit: Retrofit): EndPoints {
        return retrofit.create(EndPoints::class.java)
    }

    @Provides
    fun provideMovieDbRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMovieDbHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectivityInterceptor(context))
            .addInterceptor(NetworkResponseInterceptor())
            .connectTimeout(TIME_OUT, TimeUnit.MINUTES)
            .build()
    }
}