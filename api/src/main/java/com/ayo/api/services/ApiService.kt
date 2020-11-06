package com.ayo.api.services

import com.ayo.api.endpoints.EndPoints
import javax.inject.Inject

class ApiService @Inject constructor(private val endpoints: EndPoints) {

    suspend fun getCharacters() = endpoints.getCharacters().body()
}
