package com.ayo.domain.usecase

import com.ayo.api.services.ApiService
import com.ayo.domain.model.CharacterDomain
import com.ayo.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//if we we were persisting data we would have a repository that would have the api service
//and our persistence library
class CharacterUseCase @Inject constructor(private val apiService: ApiService) {

    fun getCharacters(): Flow<List<CharacterDomain>> = flow {
        apiService.getCharacters()?.let { characters -> emit(characters.toDomain()) }
    }

    fun getCharactersFiltered(name: String? = null, season: Int? = null): Flow<List<CharacterDomain>> =
        flow {
            apiService.getCharacters()?.let { characters ->
                val chars =
                    if (season == 0 && name != null) {
                        characters.filter { it.name.toLowerCase().contains(name.toLowerCase()) }
                            .toDomain()
                    }
                    else if (season == 0 && name == null){
                        characters.map { it.toDomain() }
                    }
                    else when {
                        name != null && season == null ->
                            characters.filter { it.name.toLowerCase().contains(name.toLowerCase()) }
                                .toDomain()
                        name == null && season != null ->
                            characters.filter { it.appearance.contains(season) }.toDomain()
                        name != null && season != null ->
                            characters.filter {
                                it.name.toLowerCase()
                                    .contains(name!!.toLowerCase()) && it.appearance.contains(season)
                            }
                                .toDomain()
                        else -> characters.map { it.toDomain() }
                    }
                emit(chars)
            }
        }
}
