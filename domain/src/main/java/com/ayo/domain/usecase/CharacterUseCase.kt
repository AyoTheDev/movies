package com.ayo.domain.usecase

import com.ayo.api.services.ApiService
import com.ayo.domain.model.CampusDomain
import com.ayo.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//if we we were persisting data we would have a repository that would have the api service
//and our persistence library
class CharacterUseCase @Inject constructor(private val apiService: ApiService) {

    fun getCharacters(): Flow<List<CampusDomain>> = flow {
        apiService.getCharacters()?.let { characters -> emit(characters.toDomain()) }
    }
}
