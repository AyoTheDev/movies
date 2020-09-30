package com.ayo.api.endpoints

import com.ayo.api.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoints {

    //max operator doesnt seem to work
    @GET("users?order=desc&sort=reputation&max=20&site=stackoverflow")
    suspend fun getUsersByName(@Query("inname") name: String): Response<ApiResponse>

    @GET("users?order=asc&max=20&sort=name&inname=a&site=stackoverflow")
    suspend fun getUsers() : Response<ApiResponse>

}