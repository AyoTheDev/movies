package com.ayo.api.endpoints

import com.ayo.api.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface EndPoints {

    @GET("api/characters")
    suspend fun getCharacters() : Response<ApiResponse>

}
