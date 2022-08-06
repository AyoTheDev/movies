package com.ayo.api.di

import android.content.Context
import com.ayo.api.endpoints.MovieDbEndpoints
import com.ayo.api.interceptors.NetworkConnectivityInterceptor
import com.ayo.api.interceptors.NetworkResponseInterceptor
import com.ayo.api.utils.addQueryParameterInterceptor
import com.rx2androidnetworking.Rx2ANRequest
import com.rx2androidnetworking.Rx2AndroidNetworking
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    companion object {
        private const val TIME_OUT = 1L
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMovieDbHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectivityInterceptor(context))
            .addInterceptor(NetworkResponseInterceptor())
            .addQueryParameterInterceptor(API_KEY_FIELD, API_KEY)
            .connectTimeout(TIME_OUT, TimeUnit.MINUTES)
            .build()
    }
}