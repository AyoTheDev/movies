package com.ayo.api.di

import com.ayo.api.endpoints.EndPoints
import com.ayo.api.services.StackApiService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {

    @Provides
    fun provideStackApiService(endPoints: EndPoints) = StackApiService(endPoints)
}