package com.ayo.api.di

import com.ayo.api.endpoints.MovieDbEndpoints
import com.ayo.api.interceptors.NetworkResponseInterceptor
import com.ayo.api.utils.addQueryParameterInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/"
        private const val API_KEY_FIELD = "api_key"
        private const val API_KEY = "d3b018581c65b4ac18d55a61391e87ac"
    }

    @Provides
    fun provideMovieDbEndpoint(retrofit: Retrofit): MovieDbEndpoints {
        return retrofit.create(MovieDbEndpoints::class.java)
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
    fun provideMovieDbHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkResponseInterceptor())
            .addQueryParameterInterceptor(API_KEY_FIELD, API_KEY)
            .build()
    }
}