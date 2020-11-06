package com.ayo.api.di

import com.ayo.api.endpoints.EndPoints
import com.ayo.api.services.ApiService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {

    @Provides
    fun provideApiService(endPoints: EndPoints) = ApiService(endPoints)
}
