package com.ayo.api.services

import com.ayo.api.endpoints.EndPoints
import javax.inject.Inject

class StackApiService @Inject constructor(private val endpoints: EndPoints) {

    suspend fun getUsersByName(name: String) = endpoints.getUsersByName(name).body()

    suspend fun getUsers() = endpoints.getUsers().body()
}