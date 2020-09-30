package com.ayo.domain.usecase

import com.ayo.api.services.StackApiService
import com.ayo.domain.model.UserDomain
import com.ayo.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//if we we were persisting data we would have a stack repository that would have the api service
//and our persistence library
class UserUseCase @Inject constructor(private val apiService: StackApiService) {

    fun getUsers(): Flow<List<UserDomain>> = flow {
        apiService.getUsers()?.items?.let { users ->
            emit(users.toDomain().sortedBy { it.displayName })
        }
    }

    fun getUsersByName(name: String): Flow<List<UserDomain>> = flow {
        apiService.getUsersByName(name)?.items?.let { users ->
            emit(users.toDomain().sortedBy { it.displayName })
        }
    }
}