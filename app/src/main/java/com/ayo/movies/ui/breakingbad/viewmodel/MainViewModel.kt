package com.ayo.movies.ui.breakingbad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ayo.api.exceptions.NoNetworkException
import com.ayo.api.exceptions.ServerException
import com.ayo.domain.model.CharacterDomain
import com.ayo.domain.usecase.*
import com.ayo.movies.common.BaseViewModel
import com.ayo.movies.common.CoroutineContextProvider
import com.ayo.movies.utils.Resource
import com.ayo.movies.utils.Resource.Success
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val characterUseCase: CharacterUseCase
) : BaseViewModel(coroutineContextProvider) {

    val charactersLiveData: LiveData<Resource<List<CharacterDomain>>>
        get() = _charactersListLiveData

    private val _charactersListLiveData = MutableLiveData<Resource<List<CharacterDomain>>>()

    init {
        _charactersListLiveData.postValue(Resource.Loading(true))
        loadCharacters()
    }

    fun loadCharacters(name: String? = null, season: Int? = null) {
        load(launch {
            try {
                val data = when  {
                    name != null || season != null -> characterUseCase.getCharactersFiltered(name, season)
                    else -> characterUseCase.getCharacters()
                }
                data.collect { users -> _charactersListLiveData.postValue(Success(users)) }
            } catch (e: NoNetworkException) {
                _charactersListLiveData
                    .postValue(Resource.Failure("Please connect to the internet", e))
                Timber.e(e)
            } catch (e: ServerException) {
                _charactersListLiveData
                    .postValue(Resource.Failure("Stack API is currently down", e))
                Timber.e(e)
            } catch (e: Exception) {
                _charactersListLiveData
                    .postValue(Resource.Failure("Problem fetching characters", e))
                Timber.e(e)
            }
        })
    }

}
