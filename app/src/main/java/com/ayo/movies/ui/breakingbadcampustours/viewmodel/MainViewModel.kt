package com.ayo.movies.ui.breakingbadcampustours.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ayo.api.exceptions.NoNetworkException
import com.ayo.api.exceptions.ServerException
import com.ayo.domain.model.CampusDomain
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

    val campusLiveData: LiveData<Resource<List<CampusDomain>>>
        get() = _campusListLiveData

    private val _campusListLiveData = MutableLiveData<Resource<List<CampusDomain>>>()

    init {
        _campusListLiveData.postValue(Resource.Loading(true))
        loadCharacters()
    }

    fun loadCharacters() {
        load(launch {
            try {
                characterUseCase.getCharacters().collect { users -> _campusListLiveData.postValue(Success(users)) }
            } catch (e: NoNetworkException) {
                _campusListLiveData
                    .postValue(Resource.Failure("Please connect to the internet", e))
                Timber.e(e)
            } catch (e: ServerException) {
                _campusListLiveData
                    .postValue(Resource.Failure("API is currently down", e))
                Timber.e(e)
            } catch (e: Exception) {
                _campusListLiveData
                    .postValue(Resource.Failure("Problem fetching data", e))
                Timber.e(e)
            }
        })
    }

}
